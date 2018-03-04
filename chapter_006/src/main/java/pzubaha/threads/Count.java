package pzubaha.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Chapter_006. Multithreading.
 * Monitor, Synchronized.
 * Required to make a class Count.
 * <p>
 * Contains solution of task 1102.
 * Class Count.
 * Created 04.03.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
