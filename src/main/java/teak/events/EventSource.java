package teak.events;

import java.util.List;
import java.util.ArrayList;

public class EventSource {
    private List<EventListener<?>> listeners = new ArrayList<>();

    public <T> void addListener(EventListener<T> listener) {
        listeners.add(listener);
    }

    public <T> void removeListener(EventListener<T> listener) {
        listeners.remove(listener);
    }

    public <T> void fireEvent(TeakEvent<T> event) {
        for (EventListener<?> listener : listeners) {
            ((EventListener<T>) listener).onEvent(event.getData());
        }
    }
}
