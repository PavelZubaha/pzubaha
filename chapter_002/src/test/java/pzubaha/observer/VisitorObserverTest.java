package pzubaha.observer;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing class Tracker.
 *
 * Test class of the VisitorObserver.
 * Lesson 10. Extratask.
 * Chapter_002. OOP.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 06.09.2017
 * @version 1.
 */
public class VisitorObserverTest {
    /**
     * Test method calculate periods and toString.
     */
    @Test
    public void whenAddSomeVisitorsAndCalculatePeriodsThenObserver() {
        //asign
        VisitorObserver visitorObserver = new VisitorObserver();
        StringBuilder sb = new StringBuilder();

        visitorObserver.addVisitor(33000, 34000);
        visitorObserver.addVisitor(33500, 33800);
        visitorObserver.addVisitor(33700, 36000);

        //act
        visitorObserver.calculatePeriods();
        for (VisitorObserver.VisitorPeriod visitorPeriod : visitorObserver.getVisitorPeriodsList()) {
            sb.append(String.format("%n%s", visitorPeriod.toString()));
        }
        String result = sb.toString();
        String expected = String.format("%n%s%n%s%n%s%n%s%n%s", "Start - 33000 end  - 33500 number of Visitors - 1", "Start - 33500 end  - 33700 number of Visitors - 2", "Start - 33700 end  - 33800 number of Visitors - 3", "Start - 33800 end  - 34000 number of Visitors - 2", "Start - 34000 end  - 36000 number of Visitors - 1");
        assertThat(result, is(expected));
    }

    /**
     * Test getMaxVisitorsPeriod method.
     */
    @Test
    public void whenAddSomeVisitorsAndGetMaxPeriodThenMaximumVisitorPeriodReturned() {
        //asign
        VisitorObserver visitorObserver = new VisitorObserver();
        visitorObserver.addVisitor(33000, 34000);
        visitorObserver.addVisitor(33500, 33800);
        visitorObserver.addVisitor(33700, 36000);

        //act
        visitorObserver.calculatePeriods();
        VisitorObserver.VisitorPeriod resultPeriod = visitorObserver.getMaxVisitorsPeriod();
        String result = resultPeriod.toString();
        String expected = "Start - 33700 end  - 33800 number of Visitors - 3";
        assertThat(result, is(expected));
    }
}