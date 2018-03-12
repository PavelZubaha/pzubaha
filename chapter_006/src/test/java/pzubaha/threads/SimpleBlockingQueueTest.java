package pzubaha.threads;
import org.junit.Test;

/**
 * Chapter_006. Multithreading.
 * 4. Wait, Notify, NotifyAll.
 * 1. Producer - Consumer template.
 * <p>
 * Contains solution of task 1098.
 * Class represents a tests of SimpleBlockingQueue.
 * Created 12.03.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleBlockingQueueTest {
    /**
     * Test method. For each poll and offer methods are created a thread which 6 times try to offer/poll queue.
     */
    @Test()
    public void whenOfferFullQueueOrPollFromEmptyThenThreadWait() {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(1);
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i != 6; i++) {
                    System.out.println(String.format("%s - try to offer", Thread.currentThread().getName()));
                    sbq.offer(i);
                    System.out.println(String.format("%s - print  offered value: %d", Thread.currentThread().getName(), i));
                }
            }
        };
        Thread consumer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i != 6; i++) {
                    System.out.println(String.format("%s - try to poll", Thread.currentThread().getName()));
                    System.out.println(String.format("%s - print polled value: %d", Thread.currentThread().getName(), sbq.poll()));
                }
            }
        };
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}