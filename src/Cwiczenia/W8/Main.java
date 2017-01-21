package Cwiczenia.W8;

public class Main {

    public static void main(String[] args) throws FullException, EmptyException {
        MyQueue<Integer> queue = new MyQueueImpl<>(3);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.isEmpty();
        queue.dequeue();

        System.out.println(queue.first());

    }

}
