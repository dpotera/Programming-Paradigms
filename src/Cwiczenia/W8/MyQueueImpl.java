package Cwiczenia.W8;

import java.util.ArrayList;

public class MyQueueImpl<E> implements MyQueue<E> {

    private ArrayList<E> list;
    private int f,r,size;

    public MyQueueImpl(int size) {
        list = new ArrayList<E>();
        for(int i=0; i<size; i++) list.add(i,null);
        f = r = 0;
        this.size = size;
    }

    private int incr(int v) {
        return v == size ? 0 : v+1;
    }

    @Override
    public void enqueue(E x) throws FullException {
        if(isFull()) throw new FullException("Queue is full");
        else {list.set(r,x); r = incr(r); }
    }

    @Override
    public void dequeue() {
        if(!isEmpty()) f = incr(f);
    }

    @Override
    public E first() throws EmptyException {
        if(isEmpty()) throw new EmptyException("Empty Queue!");
        else return list.get(f);
    }

    @Override
    public boolean isEmpty() {
        return r == f;
    }

    @Override
    public boolean isFull() {
        return r - f == -1 || r - f == size;
    }
}
