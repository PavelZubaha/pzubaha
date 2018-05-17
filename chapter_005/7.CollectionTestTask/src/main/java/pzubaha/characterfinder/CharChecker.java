package pzubaha.characterfinder;

import java.util.HashSet;

/**
 * Chapter_005.
 * Java Collection Framework.
 * Additional Task.
 * Task 50316.
 *
 * There are two strings.
 * Are there characters in second string present in the first one?
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class CharChecker {
    /**
     * Set of chars of first string.
     */
    private HashSet<Character> set = new HashSet<>();

    /**
     * Indexing string.
     * @param str some string.
     */
    public void indexString(String str) {
        if (str != null) {
            this.set = new HashSet<>(str.length(), 1F);
            for (char c : str.toCharArray()) {
                set.add(c);
            }
        }
    }

    /**
     * Check are the chars from specified string contains in indexed string.
     * @param str some string for checking.
     * @return true if all characters from specified string,
     * are contained in indexed one. Other wise false.
     */
    public boolean isCharsContais(String str) {
        boolean result = false;
        if (str != null) {
            result = true;
            for (char c: str.toCharArray()) {
                if (!set.contains(c)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
