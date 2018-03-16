package pzubaha.threads;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
    private volatile AtomicBoolean isShutDown = new AtomicBoolean(false);

    /**
     * Constructor.
     */
    public ThreadPool() {
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
     * For join all threads.
     * Use this only using shutDown method, otherwise there is deadLock probability.
     */
    public void joinAll() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get max threads in this thread pool.
     * @return max threads amount.
     */
    public int getMaxThreads() {
        return maxThreads;
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
    }

    /**
     * Runtime exception when add work after calling shutDown method.
     */
    class ThreadPoolShutDownException extends RuntimeException {
        public ThreadPoolShutDownException() {
            super("Try to add work when ThreadPool is at shutdown condition");
        }
    }

    /**
     * Try counting arithmetic progression n time in cycle, using simple ThreadPoolImplementation.
     * @param args args
     */
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        class SimpleWork implements Work {
            private AtomicInteger atomicInteger;

            public SimpleWork(AtomicInteger atomicInt) {
                atomicInteger = atomicInt;
            }

            @Override
            public void doWork() {
                for (int i = 0; i != 1001; i++) {
                    atomicInteger.getAndAdd(i);
                }
            }
        }
        for (int i = 0; i != 10; i++) {
            threadPool.add(new SimpleWork(atomicInteger));
        }
        threadPool.shutDown();
        threadPool.joinAll();
        System.out.println(atomicInteger.intValue());
    }
}

/**
 * Interface of ThreadPool works.
 */
interface Work {
    default void doWork() {
    }
}

/**
 * Simple Work implementation for testing case.
 * Calculate accumulate random value from 0 to 1000, from 0 to 1 million random times.
 * After print result.
 */
class TestWork implements Work {
    @Override
    public void doWork() {
        Random random = new Random();
        int testFor = random.nextInt(1000_000);
        int sum = 0;
        for (int i = 0; i != testFor; i++) {
            sum += random.nextInt(1000);
        }
        System.out.println(Thread.currentThread().getName() + " sum: " + sum);
    }
}