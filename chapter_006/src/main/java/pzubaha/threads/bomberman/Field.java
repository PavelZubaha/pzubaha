package pzubaha.threads.bomberman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Chapter_006. Multithreading.
 * 6. Control task. 2. Bomberman.
 * Contains solution of task 1108.
 * Realization of game field.
 * It incapsulate two-dimensional array of Point(Lock's)
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Field {
    /**
     * Game field.
     */
    private final Point[][] board;
    /**
     * Constructor.
     * @param width width of field.
     * @param height heigth of field.
     */
    public Field(int width, int height) {
        if (width < 2) {
            width = 2;
        }
        if (height < 2) {
            height = 2;
        }
        board = new Point[width][height];
        for (int i = 0; i != board.length; i++) {
            for (int k = 0; k != board[i].length; k++) {
                board[i][k] = new Point(i, k);
            }
        }
    }

    /**
     * Get lock method.
     * @param point point witch
     * @return true if current thread catch lock associated with the point coordinates,
     * otherwise false.
     */
    public final boolean tryLock(final Point point) throws InterruptedException {
        boolean result = false;
        int x = point.getX(), y = point.getY();
        if (isFieldContains(x, y)) {
            result = board[x][y].tryLock(500, TimeUnit.MILLISECONDS);
        }
        return result;
    }

    /**
     * Check is there lock with specified coordinates.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return true if there is lock != null.
     */
    public boolean isFieldContains(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != null;
    }

    /**
     * Get random near point.
     * @param point current point.
     * @return random near point if exists.
     */
    public Point getRandomNearPoint(Point point) {
        Point result = point;
        int x = point.getX(), y = point.getY();
        List<Point> possiblePoints = new ArrayList<>();
        if (isFieldContains(x + 1, y)) {
            possiblePoints.add(board[x + 1][y]);
        }
        if (isFieldContains(x, y + 1)) {
            possiblePoints.add(board[x][y + 1]);
        }
        if (isFieldContains(x - 1, y)) {
            possiblePoints.add(board[x - 1][y]);
        }
        if (isFieldContains(x, y - 1)) {
            possiblePoints.add(board[x][y - 1]);
        }
        if (possiblePoints.size() > 0) {
            result = possiblePoints.get(ThreadLocalRandom.current().nextInt(possiblePoints.size()));
        }
        return result;
    }

    /**
     * Get string representation of the field.
     * @return string.
     */
    @Override
    public String toString() {
        String lineSep = System.getProperty("line.separator");
        StringBuilder result = new StringBuilder(1024);
        result.append(lineSep);
        result.append(lineSep);
        Arrays.stream(board).forEach(points -> {
            Arrays.stream(points).forEach(point -> result.append(point.simpleString()));
            result.append(lineSep);
        });
        return result.toString();
    }

    /**
     * Get Point by coordinates.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return point instance by specified coordinates.
     */
    public Point getPointByXY(int x, int y) {
        Point result = board[0][0];
        if (isFieldContains(x, y)) {
            result = board[x][y];
        }
        return result;
    }

    /**
     * Set start position of specified unit.
     * @param unit unit to set.
     * @return if thread(Unit) got the lock of the point.
     */
    public boolean setStartPosition(Unit unit) {
        return unit.getPoint().tryLock();
    }
}
