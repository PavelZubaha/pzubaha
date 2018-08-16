package pzubaha.observer;

/**
 * Main class of the programm.
 * Lesson 10. Extratask.
 * Chapter_002. OOP.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com).
 * @version 1.
 * @since 06.09.17
 */
public class ObserverMain {
    /**
     * main method.
     * @param args arguments.
     */
    public static void main(String[] args) {
        VisitorObserver visitorObserver = new VisitorObserver();
        visitorObserver.addVisitor(33000, 34000);
        visitorObserver.addVisitor(33500, 33800);
        visitorObserver.addVisitor(33700, 36000);
        visitorObserver.calculatePeriods();
        for (VisitorObserver.VisitorPeriod visitorPeriod : visitorObserver.getVisitorPeriodsList()) {
            System.out.println(visitorPeriod);
        }
        System.out.println("Maximum period: " + visitorObserver.getMaxVisitorsPeriod());
    }
}
