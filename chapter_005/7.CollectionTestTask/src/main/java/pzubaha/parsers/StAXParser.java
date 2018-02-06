package pzubaha.parsers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Chapter_005. Collection. Pro.
 * 7.Test task. OrderBook.
 * Task: program should create Order Books according to orders from XML file.
 * Two actions supported: new order, delete exiting order.
 * After processing the whole file, print to standard output all order books generated.
 * Bid ladder is sorted from the highest bid price at the top to lowest price at the end.
 * Ask ladder – visa versa, sorted from lowest at the top to highest price at the end.
 * <p>
 * Contains solution of task 1001.
 * Class represents StAXParser.
 * Created 05.01.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StAXParser {
    private volatile boolean runFlag = true;

    /**
     * Class represents order entity.
     */
    private class OrderEntity {
        String book;
        String type; // AddOrder or DeleteOrder.
        String operation;
        String ordId;
        String vol;
        String price;
        OrderEntity(String book, String type, String operation, String ordId, String vol, String price) {
            this.book = book;
            this.type = type;
            this.operation = operation;
            this.ordId = ordId;
            this.vol = vol;
            this.price = price;
        }
    }
    private class ReaderThread extends Thread {
        InputStream is;
        XMLStreamReader reader;
        Queue<OrderEntity> queue;
        ReaderThread(Queue<OrderEntity> queue, InputStream is) {
            super();
            this.queue = queue;
            this.is = is;
            try {
                reader = XMLInputFactory.newInstance().createXMLStreamReader(is);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            String book;
            String addOrDel;
            try {
                reader.next();
                while (reader.hasNext()) {
                    if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                        if (reader.isStartElement()) {
                            book = reader.getAttributeValue(null, "book");
                            addOrDel = reader.getLocalName();
                            queue.offer(new OrderEntity(book,
                                    addOrDel,
                                    reader.getAttributeValue(null, "operation"),
                                    reader.getAttributeValue(null, "orderId"),
                                    reader.getAttributeValue(null, "volume"),
                                    reader.getAttributeValue(null, "price")));
                        }
                    }
                }
                runFlag = false;
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Class represents thread which process orders.
     */
    private class ProcessThread extends Thread {
        SortedMap<String, OrderBook> orders;
        Queue<OrderEntity> queue;
        ProcessThread(Queue<OrderEntity> queue, SortedMap<String, OrderBook> orders) {
            super();
            this.queue = queue;
            this.orders = orders;
        }
        @Override
        public void run() {
            OrderBook orderBook;
            OrderEntity re;
            while (runFlag || !queue.isEmpty()) {
                if (!queue.isEmpty()) {
                    re = queue.poll();
                    if (!orders.containsKey(re.book)) {
                        orderBook = new OrderBook(re.book);
                        orders.put(re.book, orderBook);
                    } else {
                        orderBook = orders.get(re.book);
                    }
                    if (re.type.equals("AddOrder")) {
                        orderBook.addOrder(OrderBook.OPERATION.valueOf(re.operation),
                                Integer.parseInt(re.ordId), Integer.parseInt(re.vol),
                                Float.parseFloat(re.price));
                    } else {
                        orderBook.delOrder(Integer.parseInt(re.ordId));
                    }
                } else {
                    Thread.yield();
                }
            }
            runFlag = true;
        }
    }

    /**
     * Method for parsing orders XML.
     * @param is - input data stream.
     * @return SortedMap of OrderBook's mapping key name.
     * @throws FileNotFoundException if there is no such file.
     * @throws InterruptedException if thread is interrupted when it sleeps.
     * @throws XMLStreamException The base exception for unexpected processing errors.  This Exception
     * class is used to report well-formedness errors as well as unexpected
     * processing conditions.
     */
    public SortedMap<String, OrderBook> parse(InputStream is) throws FileNotFoundException, XMLStreamException, InterruptedException {
        ConcurrentLinkedQueue<OrderEntity> bq = new ConcurrentLinkedQueue<>();
        Thread readThread = new ReaderThread(bq, is);
        readThread.start();
        SortedMap<String, OrderBook> orders = new TreeMap<>(String::compareTo);
        Thread consume = new ProcessThread(bq, orders);
        consume.start();
        readThread.join();
        consume.join();
        return orders;
    }

    /**
     * Main method for the program.
     * @param args args.
     * @throws FileNotFoundException if file not found.
     * @throws XMLStreamException unexpected
     * processing conditions.
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, InterruptedException {
        StAXParser parser = new StAXParser();
        long time = System.currentTimeMillis();
        SortedMap<String, OrderBook> map = parser.parse(new FileInputStream("C:\\projects\\orders.xml"));
        StringBuilder builder = new StringBuilder();
        for (OrderBook ob : map.values()) {
            builder.append(ob.print());
        }
        System.out.printf("%s%n%dms", builder, System.currentTimeMillis() -  time);
    }
}
