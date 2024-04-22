package teak.events;

public interface EventListener<T> {
    public void onEvent(T data);
}
