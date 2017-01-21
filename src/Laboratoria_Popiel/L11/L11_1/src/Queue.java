package Laboratoria_Popiel.L11.L11_1.src;

public interface Queue<T>{
    void enqueue(T elem);
    T top() throws Exception;
    T pop() throws Exception;
    <Q extends Queue<T>> void merge (Q second) throws Exception;
    boolean isEmpty();
}
