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
        String expected = "War and peace";
        String root = "src" + File.separator + "main"
                + File.separator + "resources";
        System.out.println(root);
        String text = "www.lib.ru";
        List<String> extension = Arrays.asList("txt");
        ParallelSearch parallelSearch = new ParallelSearch(root, text, extension);
        try {
            parallelSearch.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> result = parallelSearch.result();
        System.out.println(result);
        Assert.assertTrue(result.get(0).contains(expected));
    }
}