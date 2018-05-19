package pzubaha.characterfinder;

import java.util.HashMap;
import java.util.Iterator;

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
    private HashMap<Character, Integer> map = new HashMap<>();

    /**
     * Indexing string.
     * @param str some string.
     */
    public void indexString(String str) {
        if (str == null) {
            return;
        }
        this.map = new HashMap<>(str.length(), 1F);
        fillMap(str, map);
    }

    /**
     * Fill some HashMap with chars from specified string.
     * @param str given string as source of chars.
     * @param mapContainer map for char as key and single char amount as value.
     */
    private void fillMap(String str, HashMap<Character, Integer> mapContainer) {
        for (char c : str.toCharArray()) {
            mapContainer.compute(c, (character, integer) -> integer == null ? 1 : integer + 1);
        }
    }

    /**
     * Check are the chars from specified string contains in indexed string.
     * @param str some string for checking.
     * @return true if all characters from specified string,
     * are contained in indexed one. Other wise false.
     */
    public boolean isCharsContains(String str) {
        boolean result = false;
        if (str != null) {
            result = true;
            HashMap<Character, Integer> strCharMap = new HashMap<>(str.length(), 1F);
            fillMap(str, strCharMap);
            Iterator<Character> iterator = strCharMap.keySet().iterator();
            Character character;
            while (iterator.hasNext()) {
                character = iterator.next();
                if (!map.containsKey(character) || map.get(character) < strCharMap.get(character)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
