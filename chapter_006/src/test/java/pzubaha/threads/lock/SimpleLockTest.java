package pzubaha.threads.lock;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Chapter_006. Multithreading.
 * 4. Wait, Notify, NotifyAll.
 * 3. Simple Lock.
 * <p>
 * Contains solution of task 1100.
 * Tests of Simple Lock.
 * Created 14.03.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleLockTest {
    /**
     * Test lock.
     */
    @Test
    public void whenSomeThreadGotLockThenAnotherWaitForUnlocking() {
        SimpleLock simpleLock = new SimpleLock();
        ArrayList<Thread> threads = new ArrayList<>();
        Thread thread;
        for (int i = 0; i != 10; i++) {
            thread = new Thread() {
                @Override
                public void run() {
                    simpleLock.lock();
                    System.out.println(getName() + " got a lock");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + " unlock");
                    simpleLock.unlock();
                }
            };
            threads.add(thread);
            thread.start();
        }
        for (Thread thrd : threads) {
            try {
                thrd.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Test reentrant of Simple lock.
     */
    @Test
    public void whenOneThreadGotALockTwoTimesThenNeedsTwoTimesToUnlock() {
        SimpleLock simpleLock = new SimpleLock();
        ArrayList<Thread> threads = new ArrayList<>();
        Thread thread;
        for (int i = 0; i != 10; i++) {
            thread = new Thread() {
                @Override
                public void run() {
                    simpleLock.lock();
                    simpleLock.lock();
                    System.out.println(getName() + " got a lock 2 times");
                    try {
                        Thread.sleep(50);
                        System.out.print(getName() + " unlock ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        simpleLock.unlock();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(getName() + " unlock ");
                        simpleLock.unlock();
                    }
                }
            };
            threads.add(thread);
            thread.start();
        }
        for (Thread thrd : threads) {
            try {
                thrd.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}