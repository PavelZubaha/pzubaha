package pzubaha.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Chapter_006. Multithreading.
 * 4. Wait, Notify, NotifyAll.
 * 1. Producer - Consumer template.
 * <p>
 * Contains solution of task 1098.
 * Class represents a simple blocking bounded queue.
 * Created 12.03.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Interior collection.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    /**
     * Max capacity of this collection.
     */
    private final int capacity;

    /**
     * Constructor.
     * @param capacity max capacity.
     */
    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter for capacity of queue.
     * @return capacity of this queue.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Inserts the specified element into this queue. At the end position.
     * @param value specified element to add.
     */
    public void offer(T value) {
        synchronized (this) {
            while (queue.size() >= this.capacity) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.printf("%s interrupted while wait offer", Thread.currentThread().getName());
                }
            }
            queue.offer(value);
            this.notify();
        }
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns null if this queue is empty.
     * @return the head of this queue, or null if this queue is empty.
     */
    public T poll() throws InterruptedException {
        T value;
        synchronized (this) {
            while (queue.isEmpty()) {
                this.wait();
            }
            value =  queue.poll();
            this.notify();
        }
        return value;
    }

    /**
     * Try is queue empty ?
     * @return true if this queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }
}
