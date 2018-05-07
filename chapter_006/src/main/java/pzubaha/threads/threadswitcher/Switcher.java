package pzubaha.threads.threadswitcher;

/**
 * Chapter_006. Multithreading.
 * 6. Control tasks.
 * <p>
 * Contains solution of task 50581.
 * Needs to create some switcher, that manages two threads when they add values.
 * First thread add 10 values, than second thread add  10 values, etc.
 * Class manages threads for adding.
 * Created 30.04.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Switcher {
    /**
     * Max amount total add operations.
     */
    private final  int maxTotalCount;
    /**
     * Max amount operations of single thread sequence .
     */
    private final int maxOneThreadCount;
    /**
     * Total counter of add operations.
     */
    private volatile int totalCounter = 0;

    /**
     * Appendable holder of stings.
     */
    private final StringAsIntHolder holder = new StringAsIntHolder();
    /**
     * Current tread.
     */
    private Thread currentWorker;
    /**
     * Counter for single thread operations sequence.
     */
    private int oneThreadCount = 0;

    /**
     * Constructor.
     * @param maxCount total max operations.
     * @param maxOneThreadCount max amount operations of single thread sequence .
     */
    public Switcher(int maxCount, int maxOneThreadCount) {
        this.maxTotalCount = maxCount;
        this.maxOneThreadCount = maxOneThreadCount;
    }

    /**
     * Method for adding int value.
     * @param value int value to add.
     * @throws InterruptedException when current thread interrupted while wait.
     */
    public synchronized void addInt(int value) throws InterruptedException {
        if (totalCounter < maxTotalCount) {
            if (currentWorker != Thread.currentThread()) {
                if (currentWorker == null) {
                    currentWorker = Thread.currentThread();
                } else {
                    wait();
                }
            }
            if (oneThreadCount < maxOneThreadCount) {
                holder.appendInt(value);
                totalCounter++;
                oneThreadCount++;
            } else {
                currentWorker = null;
                notifyAll();
                oneThreadCount = 0;
                wait();
            }
        } else {
            notifyAll();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get String from holder.
     * @return string representation of added int sequence.
     */
    public  String getString() {
        synchronized (holder) {
            return holder.getString();
        }
    }

    /**
     * Method for initiation of two workers(threads).
     * @param firstVal int value that first worker add.
     * @param secondVal int value that second worker add.
     * @throws InterruptedException when this thread interrupted while waiting join.
     */
    public void initTwoWorkers(int firstVal, int secondVal) throws InterruptedException {
        Worker worker1 = new Worker(this, firstVal);
        worker1.start();
        Thread.sleep(2);
        Worker worker2 = new Worker(this, secondVal);
        worker2.start();
        worker1.join();
        worker2.join();
    }
}

/**
 * Special class for adding some value.
 */
class Worker extends Thread {
    private final Switcher switcher;
    private final int valueToAdd;

    Worker(Switcher switcher, int valueToAdd) {
        this.switcher = switcher;
        this.valueToAdd = valueToAdd;
    }
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                switcher.addInt(valueToAdd);
            } catch (InterruptedException e) {
                System.out.printf("/n%s finished by interruption", Thread.currentThread().getName());
                break;
            }
        }
    }

    /**
     * Main method.
     * @param args args
     */
    public static void main(String[] args) {
        Switcher switcher = new Switcher(22, 10);
        try {
            switcher.initTwoWorkers(1, 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(switcher.getString());
    }
}