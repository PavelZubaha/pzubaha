package pzubaha.indexing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Chapter_005.
 * Java Collection Framework.
 * Additional Task.
 * Task 50316.
 * Indexing words from file.
 * Test of created class.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class WordIndexTest {

    private File file;
    private final String test = "Test! The TEST-string!";
    private final String searchWord = "TEST";
    private final String fileName = "TEST.txt";
    private Set<Integer> positions;
    /**
     * Creating TEST file.
     */
    @Before
    public void setUp() {
        file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file.getPath())) {
            byte[] bytes = test.getBytes(StandardCharsets.UTF_8);
            fos.write(bytes);
        } catch (IOException e) {
            System.err.println("IOException when write to file " + file);
        }
    }

    /**
     * Test class methods with real positions case.
     * @throws IOException throw when file not found etc.
     */
    @Test
    public void whenLoadFileThenCallGetIndexes4WOrldThenSetReturned() throws IOException {
        WordIndex wi = new WordIndex();
        wi.loadFile(file.getPath());
        positions = wi.getIndexes4Word(searchWord);
        //check real positions of the search word "test" in the test string.
        Iterator<Integer> iterator = positions.iterator();
        assertThat(iterator.next(), is(0));
        assertThat(iterator.next(), is(10));
    }

    /**
     * Test class methods with not contained string and null.
     * @throws IOException throw when file not found etc.
     */
    @Test
    public void whenTryToGetPositionsFromWordIndexOfStringThatNotContainsInTheTextThanNullReturns() throws IOException {
        WordIndex wi = new WordIndex();
        wi.loadFile(file.getPath());
        //check null when search
        positions = wi.getIndexes4Word("te");
        assertNull(positions);
        positions = wi.getIndexes4Word(null);
        assertNull(positions);
    }

    @Test
    public void callIndexOfReturnsSameResultWithCallingGetIndexes4Word() throws IOException {
        String word4Search = "Test";
        WordIndex wi = new WordIndex();
        wi.loadFile(file.getPath());
        positions = wi.getIndexes4Word(word4Search);
        int indexOf = new String(Files.readAllBytes(Paths.get(file.getPath()))).indexOf(word4Search);
        assertThat(positions.iterator().next(), is(indexOf));
    }
    /**
     * Deleting TEST file.
     */
    @After
    public void afterAll() {
        file.delete();
    }
}