package pzubaha.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple demonstration of guaranteed deadlock condition.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class DeadLock {
    public static void main(String[] args) {
        final ReentrantLock lock_1 = new ReentrantLock();
        final ReentrantLock lock_2 = new ReentrantLock();
        new Thread() {
            @Override
            public void run() {
                lock_1.lock();
                while (!lock_2.isLocked()) {
                    yield();
                }
                System.out.println(getName());
                lock_2.lock();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                lock_2.lock();
                while (!lock_1.isLocked()) {
                    yield();
                }
                System.out.println(getName());
                lock_1.lock();
            }
        }.start();
    }
}
