package pzubaha.threads.threadswitcher;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SwitcherTest {
    Switcher switcher;
    @Before
    public void setUp() {
        switcher = new Switcher(10, 2);
    }

    @Test
    public void whenAddIntThanSwitcherContainsIt() {
        try {
            switcher.addInt(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expected = String.valueOf(1);
        assertEquals(expected, switcher.getString());
    }
}