package pzubaha.list;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_005. Collection. Pro.
 * 3. List.
 * <p>
 * Contains solution of task 160.
 * Test class Stack.
 * Created 07.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StackListTest {
    //assign
    private StackList<String> testedStack;
    private String first = String.valueOf("first");
    private String second = String.valueOf("second");
    private String third = String.valueOf("third");

    /**
     * Set up before tests.
     */
    @Before
    public void setUp() {
        testedStack = new StackList<String>();
        testedStack.push(first);
        testedStack.push(second);
        testedStack.push(third);
    }

    /**
     * Test pop method's.
     */
    @Test(expected = EmptyStackException.class)
    public void whenPopThenReturnedAndRemovedLast() {
        assertThat(testedStack.peek(), is(third));
        assertThat(testedStack.pop(), is(third));
        assertThat(testedStack.pop(), is(second));
        assertThat(testedStack.peek(), is(first));
        assertThat(testedStack.pop(), is(first));
        testedStack.pop();
    }
}