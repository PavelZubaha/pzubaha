package pzubaha.threads;

import java.io.FileReader;
import java.io.IOException;

/**
 * Chapter_006. Multithreading.
 * Threads.
 * <p>
 * Contains solution of task 1017.
 * Class represents counter witch counts
 * number of words and spaces in text file using separated threads.
 * It is need to output "End of program" after the finish of counting threads.
 * Created 06.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Counter {
    /**
     * Path to file.
     */
    private String path;

    /**
     * Setter for path.
     * @param path path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    class SpaceCounter extends Thread {
        @Override
        public void run() {
            System.out.println("Starting count spaces...");
            int count = 0;
            try {
                FileReader isr = new FileReader(path);
                while (isr.ready()) {
                    if (Character.isSpaceChar(isr.read())) {
                        count++;
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println(String.format("Found spaces: %d", count));
        }
    }

    /**
     * Method for starting count words and spaces.
     */
    public void startCounts() {
        System.out.println("Start program");
        Thread spaceCounter = new SpaceCounter();
        Thread wordsCounter = new Thread(() -> {
            System.out.println("Starting count words...");
            int count = 0;
            try {
                FileReader isr = new FileReader(path);
                while (isr.ready()) {
                    if (Character.isLetter(isr.read())) {
                        count++;
                        while (isr.ready()) {
                            if (!Character.isLetter(isr.read())) {
                                break;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Found words: %d", count));
        });
        spaceCounter.start();
        wordsCounter.start();
        try {
            spaceCounter.join();
            wordsCounter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of program");
    }
    /**
     * main method
     * @param args args.
     */
    public static void main(String[] args) {
        Counter c = new Counter();
        c.setPath("chapter_006\\src\\main\\resources\\War and peace.txt");
        c.startCounts();
    }
}
