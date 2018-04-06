package pzubaha.threads.pool;

import java.util.Random;

/**
 * Simple Work implementation for testing case.
 * Calculate accumulate random value from 0 to 1000, from 0 to 1 million random times.
 * After print result.
 */
public class TestWork implements Work {
    @Override
    public void doWork() {
        Random random = new Random();
        int testFor = random.nextInt(1000_000);
        int sum = 0;
        for (int i = 0; i != testFor; i++) {
            sum += random.nextInt(1000);
        }
        System.out.println(Thread.currentThread().getName() + " sum: " + sum);
    }
}
