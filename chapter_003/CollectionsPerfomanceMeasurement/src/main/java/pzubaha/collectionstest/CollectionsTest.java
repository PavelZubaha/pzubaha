package pzubaha.collectionstest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Chapter_003. Collections. Lite.
 * Collections Framework.
 *
 * The class contains solution of task 10022.
 * Contains methods for measuring performance operations such as add, remove  of LinkedList, ArrayList, TreeSet.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 06.09.17
 * @version 1
 */


public class CollectionsTest {
    /**
     * Random generator.
     */
    private final Random random = new Random();
    /**
     * Method measure adding elements to collection.
     * @param collection reference to tested collection instance.
     * @param amount elements amount.
     * @return time microseconds that operation take.
     */
    public long add(Collection<String> collection, int amount) {
        if (amount <= 0) {
            return 0;
        }
        long timeConsume = 0;
        long start;
        for (String element : this.generateRandomStrings(amount)) {
            start = System.nanoTime();
            collection.add(element);
            timeConsume += (System.nanoTime() - start);
        }
        return timeConsume / 1000;
    }
    /**
     * Method measure deleting elements from collection by element.
     * @param collection reference to tested collection instance.
     * @param amount elements amount
     * @return time microseconds that operation take.
     */
    public long deleteByElements(Collection<String> collection, int amount) {
        if (amount <= 0) {
            return 0;
        }
        long timeConsume = 0;
        long start;
        int randomInt;

        //Generate random strings in 2 times more then requested to remove.
        List<String> randomStrings = this.generateRandomStrings(amount * 2);

        collection.addAll(randomStrings);
        String element;

        for (int i = amount; i > 0; i--) {

            randomInt = random.nextInt(i);
            element = randomStrings.get(randomInt);
            randomStrings.remove(randomInt);

            start = System.nanoTime();
            collection.remove(element);
            timeConsume += (System.nanoTime() - start);
        }
        return timeConsume / 1000;
    }

    /**
     * Method measure removing elements from collection by Iterator.
     * @param collection collection for test.
     * @param amount amount removing elements.
     * @return time microseconds that operation take.
     */
    public long deleteByIterator(Collection<String> collection, int amount) {
        if (amount <= 0) {
            return 0;
        }
        long timeConsume = 0;
        long start;
        Collection<String> randomStrings = this.generateRandomStrings(amount + 1);
        collection.addAll(randomStrings);
        Iterator<String> iterator = collection.iterator();
        for (int i = 0; i < amount; i++) {
            start = System.nanoTime();
            iterator.next();
            iterator.remove();

            timeConsume += (System.nanoTime() - start);
        }
        return timeConsume / 1000;
    }

    /**
     * Generate String elements stored at LinkedList.
     * @param amount amount String element.
     * @return ArrayList<String> with String elements.
     */
    private List<String> generateRandomStrings(int amount) {
        List<String> randomStrings = new LinkedList<>();
        for (int i = 0; i != amount; i++) {
            randomStrings.add(String.format("%s %d", "Random value", System.nanoTime()));
        }
        return randomStrings;
    }

    /**
     * Main method.
     * @param args no need.
     */
    public static void main(String[] args) {
        CollectionsTest collectionsTest = new CollectionsTest();
        Collection<String> arrayList = new ArrayList<>(100000);
        Collection<String> linkedList = new LinkedList<>();
        Collection<String> treeSet = new TreeSet<>();

        System.out.println(String.format("Test adding %n%s%d%n%s%d%n%s%d",
                "ArrayList: ", (collectionsTest.add(arrayList, 100000)),
                "LinkedList: ", (collectionsTest.add(linkedList, 100000)),
                "TreeSet: ", (collectionsTest.add(treeSet, 100000))
        ));

        Collection<String> arrayList1 = new ArrayList<>(20000);
        Collection<String> linkedList1 = new LinkedList<>();
        Collection<String> treeSet1 = new TreeSet<>();

        System.out.println(String.format("%nTest removing by elements %n%s%d%n%s%d%n%s%d",
                "ArrayList: ", (collectionsTest.deleteByElements(arrayList1, 10000)),
                "LinkedList: ", (collectionsTest.deleteByElements(linkedList1, 10000)),
                "TreeSet: ", (collectionsTest.deleteByElements(treeSet1, 10000))
        ));

        Collection<String> arrayList2 = new ArrayList<>(20000);
        Collection<String> linkedList2 = new LinkedList<>();
        Collection<String> treeSet2 = new TreeSet<>();

        System.out.println(String.format("%nTest removing by Iterator %n%s%d%n%s%d%n%s%d",
                "ArrayList: ", (collectionsTest.deleteByIterator(arrayList2, 10000)),
                "LinkedList: ", (collectionsTest.deleteByIterator(linkedList2, 10000)),
                "TreeSet: ", (collectionsTest.deleteByIterator(treeSet2, 10000))
        ));

    }
}
