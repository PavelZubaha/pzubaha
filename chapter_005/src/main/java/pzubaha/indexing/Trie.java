package pzubaha.indexing;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Chapter_005.
 * Java Collection Framework.
 * Additional Task.
 * Task 50316.
 * Indexing words from file.
 * There are some amount of different words in the big text file.
 * Create WordIndex class that could store all positions of each word.
 * Implements two methods:
 *  - public void loadFile(String filename) should load file, and made index structure.
 *  - public Set<Integer> getIndexes4Word(String searchWord) should return set of positions
 *  of the searchWord in the file.
 *  As an index data structure should be used prefix tree(Trie).
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Trie {
    /**
     * Root of the index tree.
     */
    private final TrieNode root = new TrieNode();

    /**
     * Special class for storing words and their positions.
     */
    private class TrieNode {
        private HashMap<Character, TrieNode> children = new HashMap<>();
        private TreeSet<Integer> positions = new TreeSet<>();
        private TrieNode() {
        }
    }

    /**
     * Method for inserting specified string with specified value to the trie.
     * @param str string to insert.
     * @param position position of specified string.
     */
    public void insert(String str, int position) {
        TrieNode current = root;
        for (char c : str.toCharArray()) {
            current = current.children.computeIfAbsent(c, character -> new TrieNode());
        }
        current.positions.add(position);
    }

    /**
     * Method for getting positions of specified string.
     * @param searchWord specified string.
     * @return set of positions.
     */
    public Set<Integer> getPositions(String searchWord) {
        if (searchWord == null) {
            return null;
        }
        boolean containsWord = true;
        TrieNode current = root;
        for (char c : searchWord.toLowerCase().toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                containsWord = false;
                break;
            }
        }
        if (current.positions.size() == 0) {
            containsWord = false;
        }
        return containsWord ? current.positions : null;
    }
}
