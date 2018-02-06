package pzubaha.threads;

import java.io.FileReader;
import java.io.IOException;

/**
 * Chapter_006. Multithreading.
 * Threads.
 * <p>
 * Contains solution of task 1016.
 * Class represents counter witch counts
 * number of words and spaces in text file using separated threads.
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

    /**
     * Method for count spaces from txt file.
     */
    private void countSpaces() {
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
        new SpaceCounter().start();
    }

    /**
     * Method for counting words from txt file.
     */
    private void countWords() {
        new Thread(() -> {
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
        }).start();
    }

    /**
     * main method
     * @param args args.
     */
    public static void main(String[] args) {
        System.out.println("Start program");
        Counter c = new Counter();
        c.setPath("chapter_006\\src\\main\\resources\\War and peace.txt");
        c.countWords();
        c.countSpaces();
        System.out.println("End program");
    }
}
