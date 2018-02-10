package pzubaha.threads;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Chapter_006. Multithreading.
 * Threads.
 * <p>
 * Contains solution of task 1019.
 * Test class for CountChar, Time.
 * Created 08.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class CountCharTest {
    @Test
    public void whenCountChars() throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("Program starting...");
        Thread countThread;
        Thread timer;
        Container<Integer> counterHolder = new Container<>(0);
        try {
            countThread = new CountChar(new FileReader("src\\main\\resources\\War and peace.txt"), counterHolder);
            timer = new Time(countThread, 10);
            timer.start();
            timer.join();
            assertThat(timer.isAlive(), is(false));
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Chars: %d%nThe program finish. Time consumed: %dms", counterHolder.getObj(), System.currentTimeMillis() - start));
    }
}