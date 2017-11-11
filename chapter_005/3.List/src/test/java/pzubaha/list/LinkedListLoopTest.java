package pzubaha.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * Find loop in the list.
 * <p>
 * Contains solution of task 161.
 * Test class.
 * Created 10.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class LinkedListLoopTest {
    /**
     * Tested instances and variables.
     */
    private LinkedListLoop lLL = new LinkedListLoop();
    private LinkedListLoop.Node node1 = lLL.makeNode();
    private LinkedListLoop.Node node2 = lLL.makeNode();
    private LinkedListLoop.Node node3 = lLL.makeNode();
    private LinkedListLoop.Node node4 = lLL.makeNode();

    /**
     * Setup before testing.
     */
    @Before
    public void setUp() {
        lLL.setHead(node1);
        node1.connect(node2);
        node2.connect(node3);
        node3.connect(node4);
    }

    /**
     * Test isLoop method.
     */
    @Test
    public void whenTryIsLoopThenReturnedTrueIfThereIsALoopInTheList() {
        assertTrue(!lLL.isLoop());
        node4.connect(node1);
        assertTrue(lLL.isLoop());
    }
}