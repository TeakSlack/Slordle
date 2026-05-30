#ifndef EVENT_H
#define EVENT_H

#include "Pch.h"

struct KeyPressedEvent
{
	KeyboardKey key;
};

using Event = std::variant<
	KeyPressedEvent
>;

class EventBus {
public:
    template<typename T>
    void Subscribe(std::function<void(const T&)> callback)
    {
        m_Listeners[typeid(T)].push_back(
            [cb = std::move(callback)](const Event& e) {
                cb(std::get<T>(e));
            });
    }

    void Dispatch(const Event& event)
    {
        const auto key = std::visit([](const auto& e) {
            return std::type_index(typeid(e));
            }, event);

        auto it = m_Listeners.find(key);
        if (it != m_Listeners.end())
            for (const auto& fn : it->second)
                fn(event);
    }

private:
    using Handler = std::function<void(const Event&)>;
    std::unordered_map<std::type_index, std::vector<Handler>> m_Listeners;
};

#endif EVENT_H