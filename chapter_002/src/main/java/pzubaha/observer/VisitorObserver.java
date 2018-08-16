package pzubaha.observer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class contains methods for calculation visitors periods, actions.
 * Lesson 10. Extratask.
 * Chapter_002. OOP.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com).
 * @version 1.
 * @since 06.09.17
 */
public class VisitorObserver {

    /**
     * visitor id.
     */
    private static int iD = 0;
    /**
     * Array list for storing visitors.
     */
    private ArrayList<Visitor> visitorsList = new ArrayList<Visitor>(10);
    /**
     * Array list for storing visitors periods.
     */
    private ArrayList<VisitorPeriod> visitorPeriodsList = new ArrayList<VisitorPeriod>(10);
    /**
     * adding visitors.
     * @param joinTime - time(seconds from 0:00) when visitor enter the bank.
     * @param outTime - time(seconds from 0:00) when visitor left the bank.
     */
    public void addVisitor(int joinTime, int outTime) {
        visitorsList.add(Visitor.writeVisitor(iD++, joinTime, outTime));
    }

    /**
     * Getter for getting periods.
     * @return visitor periods ArrayList.
     */
    public ArrayList<VisitorPeriod> getVisitorPeriodsList() {
        return visitorPeriodsList;
    }
    /**
     * Class represent visitor action.
     */
    class VisitorAction implements Comparable {
        /**
         * time(seconds from 0:00) of action.
         */
        private int timeSeconds;
        /**
         * Which visitor act.
         */
        private Visitor visitor;

        /**
         * Getting action time.
         * @return time of day in seconds.
         */
        public int getTimeSeconds() {
            return timeSeconds;
        }

        /**
         * Getting visitor(person) who act.
         * @return visitor instance.
         */
        public Visitor getVisitor() {
            return visitor;
        }

        /**
         * constructor for action.
         * @param visitor - who act.
         * @param time - time of day in seconds.
         */
        VisitorAction(Visitor visitor, int time) {
            this.visitor = visitor;
            this.timeSeconds = time;
        }

        /**
         * compareTo method requred for Collection.sort method.
         * @param o - object to compare.
         * @return - integer result.
         * @throws ClassCastException - when object o couldn't be downcast.
         */
        @Override
        public int compareTo(Object o) throws ClassCastException {
            VisitorAction ca = (VisitorAction) o;
            int result;
            result = this.timeSeconds - ca.timeSeconds;
            return result;
        }
    }

    /**
     * Class represent period of visitors.
     */
    class VisitorPeriod implements Comparable {
        /**
         * Time when period starts.
         */
        private int startSeconds;
        /**
         * Time when period ends.
         */
        private int endSeconds;
        /**
         * ArrayList of visitors.
         */
        private ArrayList<Visitor> visitors;

        /**
         * getting time of starting period.
         * @return time seconds.
         */
        public int getStartSeconds() {
            return startSeconds;
        }

        /**
         * getting time of ending period.
         * @return tme seconds.
         */
        public int getEndSeconds() {
            return endSeconds;
        }

        /**
         * gettind list of visitors in the period.
         * @return - Visitor.
         */
        public ArrayList<Visitor> getVisitors() {
            return visitors;
        }

        /**
         * toString method.
         * @return - Visitor.
         */
        @Override
        public String toString() {
            return String.format("Start - %d end  - %d number of Visitors - %d", startSeconds, endSeconds, visitors.size());
        }

        /**
         * construcor for visitor periods.
         * @param startSeconds - time(seconds from 0:00) when visitor entered the bank.
         * @param endSeconds - time(seconds from 0:00) when visitor left the bank.
         * @param visitors - Visitor, who act.
         */
        VisitorPeriod(int startSeconds, int endSeconds, ArrayList<Visitor> visitors) {
            this.startSeconds = startSeconds;
            this.endSeconds = endSeconds;
            this.visitors = visitors;
        }

        /**
         * compareTo method, requred foor using Collection.sort() method.
         * @param o - object for comparind.
         * @return - integer resul.
         * @throws ClassCastException
         */
        @Override
        public int compareTo(Object o) throws ClassCastException {
            VisitorPeriod cp = (VisitorPeriod) o;
            int result = this.visitors.size() - cp.visitors.size();
            return result;
        }
    }

    /**
     * Sorting ArrayList of VisitorPeriods.
     * @param visitorPeriods - ArrayList of VisitorPeriods.
     */
    private void sortByVisitorsNumber(ArrayList<VisitorPeriod> visitorPeriods) {
        try {
            Collections.sort(visitorPeriods);
        } catch (ClassCastException cce) {
            System.out.println("Objects can't be converted to VisitorPeriod");
        }
    }

    /**
     * Getting period with maximum visitors.
     * @return Visitor period with max visitors.
     */
    public VisitorPeriod getMaxVisitorsPeriod() {
        ArrayList<VisitorPeriod> visitorPeriodsForGetMaxMethod = (ArrayList<VisitorPeriod>) visitorPeriodsList.clone();
        sortByVisitorsNumber(visitorPeriodsForGetMaxMethod);
        return visitorPeriodsForGetMaxMethod.get(visitorPeriodsForGetMaxMethod.size() - 1);
    }


    /**
     * calculation ArrayList with actions.
     * @return actions like ArrayList.
     */
    private ArrayList<VisitorAction> calculateActions() {

        ArrayList<VisitorAction> visitorActions = new ArrayList<>(20);
        for (Visitor visitor : visitorsList) {
            visitorActions.add(new VisitorAction(visitor, visitor.getJoinTimeSeconds()));
            visitorActions.add(new VisitorAction(visitor, visitor.getOutTimeSeconds()));
            try {
                Collections.sort(visitorActions);
            } catch (ClassCastException ce) {
                System.out.println("Instance can't be converted to VisitorAction");
            }
        }
        return visitorActions;
    }

    /**
     * Calculation periods.
     */
    public void calculatePeriods() {
        ArrayList<VisitorAction> visitorActions = this.calculateActions();
        ArrayList<Visitor> currentVisitors = new ArrayList<Visitor>(12);
        Visitor currentVisitor;
        for (int i = 0; i < visitorActions.size() - 1; i++) {
            currentVisitor = visitorActions.get(i).getVisitor();
            //actions if Visitor entered the bank
            if (currentVisitor.getJoinTimeSeconds() ==  visitorActions.get(i).getTimeSeconds()) {
                currentVisitors.add(currentVisitor);
                visitorPeriodsList.add(new VisitorPeriod(currentVisitor.getJoinTimeSeconds(), visitorActions.get(i + 1).getTimeSeconds(), (ArrayList<Visitor>) currentVisitors.clone()));
            } else {
                //actions if Visitor out of bank
                currentVisitors.remove(currentVisitor);
                visitorPeriodsList.add(new VisitorPeriod(currentVisitor.getOutTimeSeconds(), visitorActions.get(i + 1).getTimeSeconds(), (ArrayList<Visitor>) currentVisitors.clone()));
            }
        }
    }

}
