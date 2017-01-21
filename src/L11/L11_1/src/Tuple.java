package L11.L11_1.src;

public class Tuple<T> implements Comparable<Tuple<T>>{
    private int priority;
    private T value;

    public Tuple(T value, int priority) {
        this.priority = priority;
        this.value = value;
    }

    @Override
    public int compareTo(Tuple<T> o) {
        return Integer.compare(priority,o.priority);
    }

    public int getPriority() {
        return priority;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        Tuple<T> second = (Tuple<T>) obj;
        return priority == second.priority && value == second.value;
    }
}
