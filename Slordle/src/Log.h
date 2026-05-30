#ifndef LOG_H
#define LOG_H

class Log
{
public:
	// Meyer's Singleton -- the singlular singleton in this codebase
	// This is justified being a singleton for a few main reasons
	// 1. Loggers are inherently global -- all systems should write to the same output
	// 2. This avoids tedious prop-drilling the logger into every constructor
	// 3. The lifetime is naturally the entire program, the logger is one of the last objects destroyed
	// 4. Avoid the static initialization order fiasco
	static Log& Get()
	{
		static Log s_Instance;
		return s_Instance;
	}

	Log(const Log&) = delete;
	Log& operator=(const Log&) = delete;

	std::shared_ptr<spdlog::logger> GetLogger(const std::string& name = "core")
	{
		std::lock_guard<std::mutex> lock(m_Mutex);

		auto it = m_Loggers.find(name);
		if (it != m_Loggers.end())
			return it->second;

		// Not found — create a new logger inheriting the core logger's settings.
		const auto& core = m_Loggers.at("core");
		auto new_logger = std::make_shared<spdlog::logger>(name, m_Sinks.begin(), m_Sinks.end());
		new_logger->set_level(core->level());
		new_logger->flush_on(core->flush_level());
		spdlog::register_logger(new_logger);
		m_Loggers.emplace(name, new_logger);
		return new_logger;
	}

private:
	Log()
	{
		auto color_sink = std::make_shared<spdlog::sinks::stdout_color_sink_mt>();
		color_sink->set_pattern("%^[%H:%M:%S.%e] [%n]%$ %v");

		m_Sinks.push_back(color_sink);

		// Create the default "core" logger at startup.
		auto core_logger = std::make_shared<spdlog::logger>("core", color_sink);
		core_logger->set_level(spdlog::level::trace);
		core_logger->flush_on(spdlog::level::warn);
		spdlog::register_logger(core_logger);
		m_Loggers.insert({ "core", core_logger });
	}

	// Default core and app loggers are created at startup; additional sub-loggers are created on demand.
	std::unordered_map<std::string, std::shared_ptr<spdlog::logger>> m_Loggers;
	std::vector<spdlog::sink_ptr> m_Sinks; // shared across all loggers
	std::mutex m_Mutex; // mutex for thread-safe insertion
};

// Macros - common log levels, routed to the "core" logger by default.  Pass a name as the first argument
// to route to a sub-logger: LOG_INFO_TO("render", "frame {}", n)
#define LOG_TRACE(...)    SPDLOG_LOGGER_TRACE  (Log::Get().GetLogger(), __VA_ARGS__)
#define LOG_DEBUG(...)    SPDLOG_LOGGER_DEBUG  (Log::Get().GetLogger(), __VA_ARGS__)
#define LOG_INFO(...)     SPDLOG_LOGGER_INFO   (Log::Get().GetLogger(), __VA_ARGS__)
#define LOG_WARN(...)     SPDLOG_LOGGER_WARN   (Log::Get().GetLogger(), __VA_ARGS__)
#define LOG_ERROR(...)    SPDLOG_LOGGER_ERROR  (Log::Get().GetLogger(), __VA_ARGS__)
#define LOG_FATAL(...)    SPDLOG_LOGGER_CRITICAL(Log::Get().GetLogger(), __VA_ARGS__)

#define LOG_TRACE_TO(name, ...)    SPDLOG_LOGGER_TRACE  (Log::Get().GetLogger(name), __VA_ARGS__)
#define LOG_DEBUG_TO(name, ...)    SPDLOG_LOGGER_DEBUG  (Log::Get().GetLogger(name), __VA_ARGS__)
#define LOG_INFO_TO(name, ...)     SPDLOG_LOGGER_INFO   (Log::Get().GetLogger(name), __VA_ARGS__)
#define LOG_WARN_TO(name, ...)     SPDLOG_LOGGER_WARN   (Log::Get().GetLogger(name), __VA_ARGS__)
#define LOG_ERROR_TO(name, ...)    SPDLOG_LOGGER_ERROR  (Log::Get().GetLogger(name), __VA_ARGS__)
#define LOG_FATAL_TO(name, ...)    SPDLOG_LOGGER_CRITICAL(Log::Get().GetLogger(name), __VA_ARGS__)

#endif // LOG_H