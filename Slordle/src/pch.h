#ifndef PCH_H
#define PCH_H

#ifdef _WIN32
#define NOMINMAX
#define NOCOMM
#define NOGDI
#include <external/fix_win32_compatibility.h>
#endif

#include <raylib.h>

#include <spdlog/spdlog.h>
#include <spdlog/sinks/stdout_color_sinks.h>

#include <memory>
#include <string>
#include <unordered_map>
#include <vector>
#include <array>
#include <mutex>
#include <iostream>
#include <algorithm>
#include <functional>
#include <variant>
#include <typeindex>

#endif