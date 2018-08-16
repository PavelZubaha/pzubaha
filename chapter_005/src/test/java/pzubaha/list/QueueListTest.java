package pzubaha.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * <p>
 * Contains solution of task 160.
 * Class test Queue.
 * Represents a first-in-first-out (FIFO) queue of objects.
 * Created 09.11.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class QueueListTest {
    //assign
    private QueueList<String> testedQueue;
    private String first = String.valueOf("first");
    private String second = String.valueOf("second");
    private String third = String.valueOf("third");
    private String inserted = String.valueOf("Inserted");
    @Before
    public void setUp() {
        testedQueue = new QueueList<String>();
        testedQueue.offer(first);
        testedQueue.offer(second);
        testedQueue.offer(third);
    }

    /**
     * Test behavior of offer, poll, peek methods of Queue class.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenPollThenRetrievingHeadOfQueueInOrderFIFO() {
        assertThat(testedQueue.peek(), is(first));
        assertThat(testedQueue.poll(), is(first));
        testedQueue.add(inserted, 1);
        assertThat(testedQueue.poll(), is(second));
        assertThat(testedQueue.peek(), is(inserted));
        assertThat(testedQueue.poll(), is(inserted));
        assertThat(testedQueue.poll(), is(third));
        testedQueue.poll();
    }
}