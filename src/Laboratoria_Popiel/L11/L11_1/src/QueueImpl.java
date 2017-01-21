package Laboratoria_Popiel.L11.L11_1.src;

public class QueueImpl<T> implements Queue<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public QueueImpl() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void enqueue(T elem) {
        if(isEmpty()){
            head = tail = new Node<T>(elem);
        } else {
            tail.setNext(new Node<T>(elem));
            tail = tail.getNext();
        }
        size++;
    }

    @Override
    public T top() throws Exception {
        if(head == null) throw new Exception("Queue empty");
        else return head.getValue();
    }

    @Override
    public T pop() throws Exception {
        if(isEmpty()){
            throw new Exception("Queue empty");
        } else {
            Node<T> first = head;
            head = head.getNext();
            size--;
            return first.getValue();
        }
    }

    @Override
    public <Q extends Queue<T>> void merge(Q second) throws Exception {
        while(!second.isEmpty()) enqueue(second.pop());
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }
}
