package pzubaha.threads.parallelsearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Chapter_006. Multithreading.
 * 4. Wait. Notify. NotifyAll.
 * Search text in files.
 * <p>
 * Contains solution of task 1106.
 * Class implements search of text in files with extensions list.
 * Created 11.04.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    private volatile boolean isFinished = false;
    /**
     * Paths queue for processing.
     */
    @GuardedBy("files")
    private final Queue<Path> files = new LinkedList<>();
    /**
     * Container for the founded paths to files witch contains specified text, and has specified extensions.
     */
    @GuardedBy("paths")
    private final List<String> paths = new ArrayList<>();

    /**
     * Constructor.
     * @param root root directory for search.
     * @param text text for search.
     * @param exts list of extensions for search.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Method for walking file tree and storing results.
     * Search thread looks for files extension. If extension matches one to of the listed
     * in List<String> exts, thread add the file path to queue.
     * Read thread gets paths of files form queue and check for contains the String text.
     * @throws InterruptedException when this thread interrupted waiting join.
     */
    public void init() throws InterruptedException {
        Thread search = new Thread(() -> {
            SearchVisitor visitor = new SearchVisitor();
            Path path = Paths.get(root);
            try {
                Files.walkFileTree(path, visitor);
            } catch (IOException e) {
                e.printStackTrace();
            }
            synchronized (files) {
                isFinished = true;
                files.notifyAll();
            }
        });
        Thread read = new Thread() {
            @Override
            public void run() {
                Path path;
                synchronized (files) {
                    while (!files.isEmpty() || !isFinished) {
                        if (!Thread.currentThread().isInterrupted()) {
                            while (files.isEmpty()) {
                                try {
                                    files.wait(200);
                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
                                    System.out.println("Read thread is interrupted while wait");
                                    break;
                                }
                            }
                        }
                        path = files.poll();
                        if (path != null) {
                            try {
                                processFile(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            private void processFile(Path path) throws IOException {
                BufferedReader reader = null;
                try {
                    reader = Files.newBufferedReader(path);
                    while (reader.ready()) {
                        if (reader.readLine().contains(text)) {
                            synchronized (paths) {
                                paths.add(path.toAbsolutePath().toString());
                            }
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
        };
        search.start();
        read.start();
        search.join();
        read.interrupt();
        read.join();
    }

    /**
     * For getting result.
     * @return result as list of strings.
     */
    public List<String>  result() {
        synchronized (paths) {
            return this.paths;
        }
    }

    /**
     * Special class which instance could be used as parameter "visitor" of method:
     * java.nio.file.Files.walkFileTree(Path start, FileVisitor<? super Path> visitor).
     */
    class SearchVisitor extends SimpleFileVisitor<Path> {
        String buffer = null;
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            buffer = file.getFileName().toString();
            if (containsExt(buffer)) {
                synchronized (files) {
                    files.add(file);
                    files.notifyAll();
                }
            }
            return super.visitFile(file, attrs);
        }

        private boolean containsExt(String buffer) {
            boolean result = false;
            for (String ext : exts) {
                if (buffer.length() >= ext.length() + 1 && buffer.lastIndexOf(ext) - 1 == buffer.lastIndexOf('.')) {
                    result = true;
                    break;
                }
            }
            return result;
        }
    }

    /**
     * Main.
     * @param args args
     */
    public static void main(String[] args) {
        ParallelSearch parallelSearch = new ParallelSearch("", "main", Arrays.asList("java"));
        try {
            parallelSearch.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parallelSearch.result().forEach(System.out::println);
    }
}
