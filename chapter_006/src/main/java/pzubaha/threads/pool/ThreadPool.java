package pzubaha.threads.pool;

import pzubaha.threads.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Chapter_006. Multithreading.
 * 4. Wait, Notify, NotifyAll.
 * 2. Thread Pool realization.
 * <p>
 * Contains solution of task 1099.
 * Class represents a simple thread pool.
 * Created 13.03.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ThreadPool {
    /**
     * Works bounded blocking queue.
     */
    private final SimpleBlockingQueue<Work> works = new SimpleBlockingQueue<>(128);
    /**
     * Max running threads.
     */
    private final int maxThreads = Runtime.getRuntime().availableProcessors();
    /**
     * Holder for threads references.
     */
    private ArrayList<Thread> threads = new ArrayList<>(maxThreads);
    /**
     * Shutdown flag.
     */
    private AtomicBoolean isShutDown = new AtomicBoolean(false);

    /**
     * Constructor.
     */
    public ThreadPool() {
    }

    /**
     * Init method for the pool.
     */
    public void init() {
        Thread thread;
        for (int i = 0; i != maxThreads; i++) {
            thread = new Thread() {
                @Override
                public void run() {
//                    System.out.println(getName() + " start");
                    Work work;
                    while (!isInterrupted() || !works.isEmpty()) {
                        try {
                            work = works.poll();
                            if (work != null) {
                                work.doWork();
                            }
                        } catch (InterruptedException e) {
//                            continue;
                        }
                    }
//                    System.out.println(getName() + " finished");
                }
            };
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * Add work for execution queue.
     * @param work specified work for add.
     */
    public void add(Work work) {
        if (isShutDown.get()) {
            throw new ThreadPoolShutDownException();
        }
        works.offer(work);
    }
    /**
     * For shutdown this pool.
     * All added before works will be finished.
     */
    public void shutDown() {
        isShutDown.set(true);
        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Runtime exception when add work after calling shutDown method.
     */
    public class ThreadPoolShutDownException extends RuntimeException {
        public ThreadPoolShutDownException() {
            super("Try to add work when ThreadPool is at shutdown condition");
        }
    }

}

