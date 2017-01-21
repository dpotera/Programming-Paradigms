package Laboratoria_Popiel.L11.L11_1.test;

import Laboratoria_Popiel.L11.L11_1.src.PriorityQueue;
import Laboratoria_Popiel.L11.L11_1.src.Tuple;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {
    private PriorityQueue<Integer> queue;

    @Before
    public void before(){
        queue = new PriorityQueue<>();
    }

    @Test
    public void initTest() throws Exception {
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(8);
        assertEquals(8,(int)queue.pop());
        assertEquals(5,(int)queue.pop());
        assertEquals(3,(int)queue.pop());
    }

    @Test
    public void tupleQueueTest() throws Exception {
        PriorityQueue<Tuple<String>> queue = new PriorityQueue<>();
        queue.enqueue(new Tuple<>("Six",6));
        queue.enqueue(new Tuple<>("Ten",10));
        queue.enqueue(new Tuple<>("Five",5));

        assertEquals(new Tuple<>("Ten",10),queue.pop());
        assertEquals(new Tuple<>("Six",6),queue.pop());
        assertEquals(new Tuple<>("Five",5),queue.pop());
    }

    @Test(expected = Exception.class)
    public void emptyQueuePopTest() throws Exception {
        queue.pop();
    }

    @Test(expected = Exception.class)
    public void emptyQueueTopTest() throws Exception {
        queue.top();
    }

    @Test
    public void mergeTest() throws Exception {
        queue.enqueue(2);
        queue.enqueue(5);
        PriorityQueue<Integer> second = new PriorityQueue<>();
        second.enqueue(7);
        second.enqueue(3);
        queue.merge(second);
        assertEquals(7,(int)queue.pop());
        assertEquals(5,(int)queue.pop());
        assertEquals(3,(int)queue.pop());
        assertEquals(2,(int)queue.pop());
    }
}
