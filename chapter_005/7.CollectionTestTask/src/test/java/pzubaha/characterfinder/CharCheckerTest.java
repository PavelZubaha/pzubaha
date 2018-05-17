package pzubaha.characterfinder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Chapter_005.
 * Java Collection Framework.
 * Additional Task.
 * Task 50316.
 *
 * There are two strings.
 * Are there characters in second string present in the first one?
 *
 * Test class for required class CharChecker.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class CharCheckerTest {
    CharChecker cc;
    String first;
    @Before
    public void setUp() throws Exception {
        cc = new CharChecker();
    }

    @Test
    public void whenFirstStringContainsAllOfSecondStringCharsThenReturnTrueOtherwiseFalse() {
        first = "abcdefghijklmn";
        cc.indexString(first);
        assertTrue(cc.isCharsContais("dca"));
        assertTrue(cc.isCharsContais("dca"));
        assertFalse(cc.isCharsContais("123"));
    }

    @Test
    public void whenFirstOrSecondStringIsNullThenReturnsFalse() {
        cc.indexString(null);
        assertFalse(cc.isCharsContais("a"));
        cc.indexString("A");
        assertFalse(cc.isCharsContais(null));
    }

}