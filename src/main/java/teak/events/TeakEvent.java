package teak.events;

public class TeakEvent<T> {
    private T data;

    public TeakEvent(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
