package pzubaha.threads.parallelsearch;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Chapter_006. Multithreading.
 * 4. Wait. Notify. NotifyAll.
 * Search text in files.
 * <p>
 * Test class parallel search.
 * Created 11.04.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ParallelSearchTest {

    @Test
    public void whenTryToFindTextInJavaFileThenPathFinded() {
        String expected = "ParallelSearch";
        String root = "src" + File.separator + "test" + File.separator + "java";
        String text = "Text to find";
        List<String> extension = Arrays.asList("java");
        ParallelSearch parallelSearch = new ParallelSearch(root, text, extension);
        try {
            parallelSearch.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = parallelSearch.result().get(0);
        Assert.assertTrue(result.contains(expected));
    }
}