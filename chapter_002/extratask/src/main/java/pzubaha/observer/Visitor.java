package pzubaha.observer;

/**
 * The class represents Visitor.
 * Lesson 10. Extratask.
 * Chapter_002. OOP.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com).
 * @since 06.09.17
 * @version 1.
 */
public class Visitor {
    /**
     * Visitor id.
     */
    private int id;
    /**
     * When Visitor enter the bank.
     */
    private int joinTimeSeconds;
    /**
     * When Visitor out of bank.
     */
    private int outTimeSeconds;
    /**
     * Bank work from 8:00 to 20:00.
     * Static variables represent it in seconds from midnight like integer values.
     */
    private static final int MAX_TIME = 3600 * 20;
    /**
     * Bank work from 8:00 to 20:00.
     * Static variables represent it in seconds from midnight like integer values.
     */
    private static final int MIN_TIME = 3600 * 8;

    /**
     * factory method to create a visitor instance.
     * @param id - visitor id.
     * @param joinTimeSeconds - when visitor enter the bank in seconds from midnight like integer value.
     * @param outTimeSeconds - when visitor out the bank in seconds from midnight like integer value.
     * @return visitor instance.
     * @throws SetVisitorTimeException - throws when visitor time(enter or out) does not included in time interval from 8:00 to 20:00.
     */
    public static Visitor writeVisitor(int id, int joinTimeSeconds, int outTimeSeconds) throws SetVisitorTimeException {
        return new Visitor(id, joinTimeSeconds, outTimeSeconds);
    }

    /**
     *
     * @param id - visitor id.
     * @param joinTimeSeconds - when visitor enter the bank in seconds from midnight like integer value.
     * @param outTimeSeconds - when visitor out the bank in seconds from midnight like integer value.
     */
    private Visitor(int id, int joinTimeSeconds, int outTimeSeconds)  {

        //time should be included in bank working time interval.
        if (outTimeSeconds > MAX_TIME
                || joinTimeSeconds <= MIN_TIME
                || joinTimeSeconds > MAX_TIME
                || outTimeSeconds <= MIN_TIME
                || outTimeSeconds <= joinTimeSeconds) {
            throw new SetVisitorTimeException("Visiting time is from 8:00 to 20:00");
        }
        this.joinTimeSeconds = joinTimeSeconds;
        this.outTimeSeconds = outTimeSeconds;
        this.id = id;
    }

    /**
     * getter for id.
     * @return - visitor id.
     */
    public long getId() {
        return id;
    }

    /**
     * getter for enter time.
     * @return - visitor enter time in seconds from midnight like integer value.
     */
    public int getJoinTimeSeconds() {
        return this.joinTimeSeconds;
    }

    /**
     * getter for out time.
     * @return - visitor enter time in seconds from midnight like integer value.
     */
    public int getOutTimeSeconds() {
        return this.outTimeSeconds;
    }

    /**
     * Exception when time of entering or escaping does not belong to bank working interval.

     */
    class SetVisitorTimeException extends RuntimeException {
        /**
         * @param message showed text when exception has happened.
         */
        SetVisitorTimeException(String message) {
            System.out.println();
        }
    }
}
