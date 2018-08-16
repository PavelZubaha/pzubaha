package pzubaha.characterfinder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Chapter_005.
 * Java Collection Framework.
 * Additional Task.
 * Task 53676.
 *
 * There are two strings.
 * Are there characters in second string present in the first one?
 *
 * Test class for required class CharChecker.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class CharCheckerTest {
    private CharChecker cc;
    private String first;
    @Before
    public void setUp() throws Exception {
        cc = new CharChecker();
    }

    @Test
    public void whenFirstStringContainsAllOfSecondStringCharsThenReturnTrueOtherwiseFalse() {
        first = "abcdefghijklmn";
        cc.indexString(first);
        assertTrue(cc.isCharsContains("dca"));
        assertTrue(cc.isCharsContains("dca"));
        assertFalse(cc.isCharsContains("123"));
    }

    @Test
    public void whenFirstOrSecondStringIsNullThenReturnsFalse() {
        cc.indexString(null);
        assertFalse(cc.isCharsContains("a"));
        cc.indexString("A");
        assertFalse(cc.isCharsContains(null));
    }
    @Test
    public void whenStrigsHasDuplicatesItConsiderTheirsAmount() {
        first = "aab";
        cc.indexString(first);
        assertFalse(cc.isCharsContains("abb"));
        assertTrue(cc.isCharsContains("baa"));
    }
}