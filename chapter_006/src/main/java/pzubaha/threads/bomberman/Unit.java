package pzubaha.threads.bomberman;

/**
 * Chapter_006. Multithreading.
 * 6. Control task. 2. Bomberman.
 * Contains solution of task 1108.
 * Default realization of unit.
 * When it had started, then it tries to move on random
 * near placed point.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Unit extends Thread {
    /**
     * Field where is Unit located.
     */
    private final Field field;
    /**
     * Point where unit placed.
     */
    private Point point;
    /**
     * Timeout for unit.
     */
    private final int timeOut;
    /**
     * Constructor.
     * @param field field.
     * @param x initial x coordinate.
     * @param y initial y coordinate.
     */
    public Unit(ThreadGroup group, Field field, int x, int y, int timeOut, String name) {
        super(group, name);
        this.field = field;
        this.point = field.getPointByXY(x, y);
        this.timeOut = timeOut;
    }

    /**
     * When it had started, then it tries to move on random
     * near placed point.
     */
    @Override
    public void run() {
        field.setStartPosition(this);
        Point nearPoint;
        while (!isInterrupted()) {
            nearPoint = pointForMoving();
            try {
                if (point != nearPoint && field.tryLock(nearPoint)) {
                    point.unlock();
                    point = nearPoint;
                }
            } catch (InterruptedException e) {
                break;
            }
            try {
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * Getting point for moving.
     * @return next point.
     */
    public Point pointForMoving() {
        return field.getRandomNearPoint(point);
    }

    /**
     * Get current unit point.
     * @return current point of unit.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Get field.
     * @return this field.
     */
    public Field getField() {
        return field;
    }
}
