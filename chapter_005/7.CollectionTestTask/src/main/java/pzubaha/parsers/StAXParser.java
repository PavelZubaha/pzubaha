package pzubaha.parsers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

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
    /**
     * Method for parsing orders XML.
     * @param is input stream for parsing.
     * @return SortedMap of OrderBook's mapping key name.
     * @throws FileNotFoundException if there is no such file.
     * @throws XMLStreamException The base exception for unexpected processing errors.  This Exception
     * class is used to report well-formedness errors as well as unexpected
     * processing conditions.
     */
    public SortedMap<String, OrderBook> parse(InputStream is) throws FileNotFoundException, XMLStreamException {
        SortedMap<String, OrderBook> orders = new TreeMap<>(String::compareTo);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);
        OrderBook orderBook;
        String book;
        reader.next();
        while (reader.hasNext()) {
            if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                if (reader.isStartElement()) {
                    if (reader.getLocalName().charAt(0) == 'A') {
                        book = reader.getAttributeValue(0);
                        if (!orders.containsKey(book)) {
                            orderBook = new OrderBook(book);
                            orders.put(book, orderBook);
                        } else {
                            orderBook = orders.get(book);
                        }
                        orderBook.addOrder(OrderBook.OPERATION.valueOf(reader.getAttributeValue(1)),
                                Integer.parseInt(reader.getAttributeValue(4)),
                                Integer.parseInt(reader.getAttributeValue(3)),
                                Float.parseFloat(reader.getAttributeValue(2)));
                    } else {
                        orders.get(reader.getAttributeValue(0)).delOrder(Integer.parseInt(reader.getAttributeValue(1)));
                    }
                }
            }
        }
        return orders;
    }

    /**
     * Main method for the program.
     * @param args args.
     * @throws FileNotFoundException if file not found.
     * @throws XMLStreamException unexpected
     * processing conditions.
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        StAXParser parser = new StAXParser();
        long start = System.currentTimeMillis();
        SortedMap<String, OrderBook> map = parser.parse(new FileInputStream("C:\\projects\\orders.xml"));
        StringBuilder builder = new StringBuilder();
        for (OrderBook ob : map.values()) {
            builder.append(ob.print());
        }
        System.out.println(builder);
        System.out.println(System.currentTimeMillis() - start);
    }
}
