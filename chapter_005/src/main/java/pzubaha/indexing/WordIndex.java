package pzubaha.indexing;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class WordIndex {
    /**
     * Stored index.
     */
    private Trie index;

    /**
     * Method for indexing file.
     * @param filePath specified file
     */
    public void loadFile(String filePath) throws IOException {
        index = new Trie();
        Pattern p = Pattern.compile("[^\\s\\[\\]#/.,\\-+:;\'\"!?()—*\\\\{}«»\\$=•\\t]+");
//        Pattern p = Pattern.compile("[\\s\\[\\]#/.,\\-+:;\'\"!?()—*\\\\{}«»\\$=•\\t]");
        Path path = Paths.get(filePath);
        String buffer;
        int position = 0;
//        try (Scanner scanner = new Scanner(path)) {
//            scanner.useDelimiter(p);
//            while (scanner.hasNext()) {
//                buffer = scanner.next().toLowerCase();
//                if (buffer.length() > 0) {
//                    index.insert(buffer, position);
//                    position += buffer.length();
//                }
//                position++;
//            }
//        } catch (IOException e) {
//            throw new IOException(e.getMessage());
//        }
        try (BufferedReader bufferedReader = Files.newBufferedReader(path);) {
            Matcher matcher;
            int current;
            while ((buffer = bufferedReader.readLine()) != null) {
                matcher = p.matcher(buffer);
                while (matcher.find()) {
                    current = matcher.start();
                    index.insert(matcher.group().toLowerCase(), current + position);
                }
                position += buffer.length() + 1;
            }
        }
    }

    /**
     * Get set of position of specified String in the file.
     * @param searchWord word to find.
     * @return set of word positions in the file.
     */
    public Set<Integer> getIndexes4Word(String searchWord) {
        return index.getPositions(searchWord);
    }

    public static void main(String[] args) throws IOException {
        String file = "chapter_005\\7.CollectionTestTask\\src\\main\\resources\\Bash_scripts.txt";
        WordIndex wi = new WordIndex();
        wi.loadFile(file);
        System.out.println(wi.getIndexes4Word("bash"));
        System.out.println(new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8).lastIndexOf("bash"));
        }
}
