package pzubaha.threads.bomberman;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Chapter_006. Multithreading.
 * 6. Control task. 2. Bomberman.
 * Contains solution of task 1108.
 * Realization of Point.
 * It has incapsulated reentrantLock.
 * Also it has x,y coordinates.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Point {
    /**
     * Lock stored in the point instance.
     */
    private final ReentrantLock reentrantLock = new ReentrantLock();
    /**
     * Owner of this point reentrantLock.
     */
    private  String owner = "O";
    /**
     * Coordinates of the  point.
     */
    private final int x, y;

    /**
     * Cunstructor
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Current thread tries to get reentrant lock with some timeout.
     * @param timeout timeout of trying.
     * @param timeUnit time.unit
     * @return true if current thread got lock, false otherwise.
     * @throws InterruptedException if interrupted.
     */
    public boolean tryLock(long timeout, TimeUnit timeUnit) throws InterruptedException {
        boolean result = false;
        if (reentrantLock.tryLock(timeout, timeUnit)) {
            owner = Thread.currentThread().getName();
            result = true;
        }
        return result;
    }

    /**
     * Current thread tries to get reentrant lock without any timeout.
     * @return true if current thread got lock, false otherwise.
     */
    public boolean tryLock() {
        boolean result = false;
        if (reentrantLock.tryLock()) {
            owner = Thread.currentThread().getName();
            result = true;
        }
        return result;
    }

    /**
     * Release lock that got before.
     */
    public void unlock() {
        reentrantLock.unlock();
        owner = "O";
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x
                && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("Point{x=%d, y=%d owner=%s}", x, y, owner);
    }

    /**
     * Simple string representation
     * @return String representation of this Point.
     */
    protected String simpleString() {
        int cellSize = owner.length();
        return String.format("%4s", owner.charAt(0) == 'O' ? "O" : owner);
    }
}
