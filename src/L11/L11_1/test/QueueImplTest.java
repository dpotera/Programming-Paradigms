package L11.L11_1.test;

import L11.L11_1.src.QueueImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueImplTest {
    private QueueImpl<Integer> queue;

    @Before
    public void before(){
        queue = new QueueImpl<>();
    }

    @Test
    public void initTest() throws Exception {
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        assertEquals(3,(int)queue.pop());
        assertEquals(2,(int)queue.pop());
        assertEquals(1,(int)queue.pop());
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
        queue.enqueue(1);
        queue.enqueue(2);
        QueueImpl<Integer> second = new QueueImpl<>();
        second.enqueue(3);
        second.enqueue(4);
        queue.merge(second);
        assertEquals(1,(int)queue.pop());
        assertEquals(2,(int)queue.pop());
        assertEquals(3,(int)queue.pop());
        assertEquals(4,(int)queue.pop());
    }

}
