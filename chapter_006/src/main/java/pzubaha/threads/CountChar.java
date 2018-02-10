package pzubaha.threads;

import java.io.IOException;
import java.io.Reader;

/**
 * Chapter_006. Multithreading.
 * Threads.
 * <p>
 * Contains solution of task 1019
 * Class represents how should threads correctly stops.
 * Created 08.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class CountChar extends Thread implements Runnable {
    private Reader r;
    private Container<Integer> counter;
    public CountChar(Reader r, Container<Integer> counterVal) {
        this.r = r;
        this.counter = counterVal;
    }

    @Override
    public void run() {
        System.out.println("Counter thread is started");
        int primitiveCounter = 0;
        try {
            while (true) {
                if (!this.isInterrupted()) {
                    if (r.ready()) {
                        r.read();
                        primitiveCounter++;
                    } else {
                        r.close();
                        System.out.println("Counting finished...");
                        break;
                    }
                } else {
                    r.close();
                    System.out.println("Count thread is interrupted before it finished counts.");
                    break;
                }
            }
            System.out.println(String.format("Chars counted: %d", primitiveCounter));
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter.setObj(primitiveCounter);
    }
}

