package pzubaha.parsers;

import org.junit.Test;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

/**
 * Chapter_005. Collection. Pro.
 * 7.Test task. OrderBook.
 * Task: program should create Order Books according to orders from XML file.
 * Two actions supported: new order, delete exiting order.
 * After processing the whole file, print to standard output all order books generated.
 * <p>
 * Contains solution of task 1001.
 * Class represents Test of SAXParser.
 * Created 05.01.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SAXParserTest {
    /**
     * Test SAXParser.
     * @throws IOException exceptions produced by failed or
     * interrupted I/O operations.
     * @throws XMLStreamException unexpected
     * processing conditions.
     */
    @Test
    public void whenInputSomeXMLThenParserShouldParseItCorrectly() throws IOException, XMLStreamException, InterruptedException {
        StringBuilder builder = new StringBuilder(256);
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        builder.append("<Orders>");
        builder.append("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"10\" orderId=\"1\" />");
        builder.append("<AddOrder book=\"book-1\" operation=\"BUY\" price=\" 99.40\" volume=\"10\" orderId=\"5\" />");
        builder.append("<AddOrder book=\"book-3\" operation=\"BUY\" price=\" 99.50\" volume=\"10\" orderId=\"4\" />");
        builder.append("<AddOrder book=\"book-3\" operation=\"SELL\" price=\" 99.50\" volume=\"20\" orderId=\"5\" />");
        builder.append("<AddOrder book=\"book-1\" operation=\"SELL\" price=\" 99.50\" volume=\"20\" orderId=\"6\" />");
        builder.append("<DeleteOrder book=\"book-1\" orderId=\"1\" />");
        builder.append("</Orders>");
        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes(StandardCharsets.UTF_8.name()));
        StAXParser parser = new StAXParser();
        SortedMap<String, OrderBook> map = parser.parse(in);
        StringBuilder resultBuilder = new StringBuilder();
        for (OrderBook ob : map.values()) {
            resultBuilder.append(ob.print());
        }
        System.out.println(resultBuilder);
    }
}