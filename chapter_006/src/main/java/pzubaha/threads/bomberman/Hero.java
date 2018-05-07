package pzubaha.threads.bomberman;

/**
 * Chapter_006. Multithreading.
 * 6. Control task. 2. Bomberman.
 * Contains solution of task 1108.
 * Class represents Hero.
 * Provides API for setting moving direction.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Hero extends Unit {
    /**
     * Time out of setting hero moving.
     */
    private static final int HERO_TIME_OUT = 1000;
    /**
     * Current direction of Hero moving.
     */
    private Direction direction = Direction.Standby;

    public Hero(ThreadGroup group, Field field, int x, int y, String name) {
        super(group, field, x, y, HERO_TIME_OUT, name);
    }

    /**
     * Get current direction.
     * @return current direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * By setting direction you can manage hero moving.
     * @param direction direction of moving.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Get next point on moving way.
     * @return next point.
     */
    @Override
    public Point pointForMoving() {
        Point result;
        int x = getPoint().getX(), y = getPoint().getY();
        switch (direction) {
            case North:
                y++;
                break;
            case West:
                x--;
                break;
            case East:
                x++;
                break;
            case South:
                y--;
                break;
            default: break;
        }
        result = getField().getPointByXY(x, y);
        return result;
    }
}
