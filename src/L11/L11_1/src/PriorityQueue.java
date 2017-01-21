package L11.L11_1.src;

public class PriorityQueue<T extends Comparable> implements Queue<T> {
    private Node<T> head;
    private int size;

    public PriorityQueue() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public <Q extends Queue<T>> void merge(Q second) throws Exception {
        while(!second.isEmpty()) enqueue(second.pop());
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
            size--;
            head = head.getNext();
            return first.getValue();
        }
    }

    @Override
    public void enqueue(T elem) {
        if(head == null){
            head = new Node<>(elem);
        } else if(head.getValue().compareTo(elem) < 0) {
            head = new Node<T>(elem,head);
        } else {
            Node<T> tmp = head;
            while(tmp.getNext() != null && tmp.getNext().getValue().compareTo(elem) >= 0)
                tmp = tmp.getNext();
            tmp.setNext(new Node<T>(elem,tmp.getNext()));
        }
        size++;
    }
}
