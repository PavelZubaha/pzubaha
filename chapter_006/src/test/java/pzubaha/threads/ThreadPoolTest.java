package pzubaha.threads;


import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Chapter_006. Multithreading.
 * 4. Wait, Notify, NotifyAll.
 * 2. Thread Pool realization.
 * <p>
 * Contains solution of task 1099.
 * Tests of thread pool.
 * Created 14.03.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ThreadPoolTest {
    private ThreadPool threadPool;
    @Before
    public void setUp() {
        threadPool = new ThreadPool();
    }

    /**
     * Test ThreadPool through counting arithmetic progression(AP) n times and accumulate results.
     * There is created 100 tasks and each has 1001 element(0, 1, .... 1000).
     * According to formula for AP sum: S = n * (A1 + An) / 2.
     * In this case we have S = 1001 * (0 + 1000) / 2 = 500500.
     * Total result for 100 task accumulation should be 500500 * 100 = 50_050_000.
     */
    @Test
    public void resultOfPerforming100TasksShouldBeDefined() {
        final int expectedResult = 50_050_000;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        class SimpleWork implements Work {
            private AtomicInteger atomicInteger;

            public SimpleWork(AtomicInteger atomicInt) {
                atomicInteger = atomicInt;
            }

            @Override
            public void doWork() {
                for (int i = 0; i != 1001; i++) {
                    atomicInteger.addAndGet(i);
                }
            }
        }
        for (int i = 0; i != 100; i++) {
            threadPool.add(new SimpleWork(atomicInteger));
        }
        threadPool.shutDown();
        threadPool.joinAll();
        assertThat(expectedResult, is(atomicInteger.intValue()));
    }

    /**
     * Test add 10 tasks for execution.
     * Then shutdown thread pool.
     * Then trying to add one more task should throw exception.
     */
    @Test(expected = ThreadPool.ThreadPoolShutDownException.class)
    public void addAfterShutDownShouldThrowsException() throws InterruptedException {
        for (int i = 0; i != 10; i++) {
            threadPool.add(new TestWork());
        }
        threadPool.shutDown();
        threadPool.add(null);
    }
}