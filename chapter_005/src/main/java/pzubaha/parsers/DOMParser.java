package pzubaha.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Chapter_005. Collection. Pro.
 * 7.Test task. OrderBook.
 * Tack: program should create Order Books according to orders from XML file.
 * Two actions supported: new order, delete exiting order.
 * After processing the whole file, print to standard output all order books generated.
 * <p>
 * Contains solution of task 1001.
 * Class represents Order Book.
 * Created 05.01.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class DOMParser {
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = dbf.newDocumentBuilder();

    public DOMParser() throws ParserConfigurationException, IOException {
    }
    public SortedMap<String, OrderBook> parse(InputStream is) throws IOException, SAXException {
        SortedMap<String, OrderBook> orders = new TreeMap<>(String::compareTo);
        long start = System.currentTimeMillis();
        Document doc = builder.parse(is);
        System.out.println(System.currentTimeMillis() - start);
        Element element = doc.getDocumentElement();
        NodeList nl = element.getChildNodes();
        NamedNodeMap nnp;
        OrderBook orderBook;
        Node node;
        String bookName;
        //When parse 300 000 elements it takes 11 - 20 sec.
        //Parsing more 320 000 - 360 000 throws OutOfMemoryError.
        for (int i = 0; i < Math.min(200000, nl.getLength()); i++) {
            node = nl.item(i);
            if (!(node instanceof Element)) {
                continue;
            }
            nnp = node.getAttributes();
            bookName = nnp.getNamedItem("book").getNodeValue();
            if (node.getNodeName().contains("AddOrder")) {
                if (!orders.containsKey(bookName)) {
                    orderBook = new OrderBook(bookName);
                    orders.put(bookName, orderBook);
                } else {
                    orderBook = orders.get(bookName);
                }
                orderBook.addOrder((nnp.getNamedItem("operation").getNodeValue().compareTo("BUY") == 0 ? OrderBook.OPERATION.BUY : OrderBook.OPERATION.SELL),
                        Integer.parseInt(nnp.getNamedItem("orderId").getNodeValue()),
                        Integer.parseInt(nnp.getNamedItem("volume").getNodeValue()),
                        Float.valueOf(nnp.getNamedItem("price").getNodeValue()));
            } else {
                orders.get(bookName).delOrder(Integer.parseInt(nnp.getNamedItem("orderId").getNodeValue()));
            }
        }
        return orders;
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        DOMParser dp = new DOMParser();
        long start = System.currentTimeMillis();
        SortedMap<String, OrderBook> books = dp.parse(new FileInputStream("C:\\projects\\orders.xml"));
        StringBuilder builder = new StringBuilder(1024);
        for (OrderBook book : books.values()) {
            builder.append(book.print());
        }
        System.out.println(builder);
        System.out.println(System.currentTimeMillis() - start);
    }
}
