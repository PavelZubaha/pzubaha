package pzubaha.threads;

/**
 * Chapter_006. Multithreading.
 * Threads.
 * <p>
 * Contains solution of task 1019.
 * Class represents simple timer for another thread.
 * Created 08.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Time extends Thread implements Runnable {
    private Thread another;
    private long workTimeMs;

    public Time(Thread another, long workTimeMs) {
        this.another = another;
        this.workTimeMs = workTimeMs;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        another.start();
        while (true) {
            if (System.currentTimeMillis() - start > workTimeMs) {
                System.out.println("Time thread finish and interrupt thread of counting chars");
                another.interrupt();
                try {
                    another.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                Thread.yield();
            }
        }
    }
}
