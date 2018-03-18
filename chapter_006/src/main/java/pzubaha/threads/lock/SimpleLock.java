package pzubaha.threads.lock;

/**
 * Chapter_006. Multithreading.
 * 4. Wait, Notify, NotifyAll.
 * 3. Simple Lock.
 * <p>
 * Contains solution of task 1100.
 * Class represents a simple lock.
 * Created 14.03.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SimpleLock {
    /**
     * Thread which take the lock.
     */
    private Thread owner = null;
    /**
     * Count of entrant of owner thread.
     */
    private int reEntrantCount = 0;

    /**
     * Lock method.
     */
    public void lock() {
        synchronized (this) {
            while (reEntrantCount != 0 && owner != Thread.currentThread()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            owner = Thread.currentThread();
            reEntrantCount++;
        }
    }

    /**
     * Unlock method.
     */
    public void unlock() {
        synchronized (this) {
            if (owner == Thread.currentThread()) {
                reEntrantCount--;
                if (reEntrantCount == 0) {
                    owner = null;
                    this.notifyAll();
                }
            }
        }
    }
}
