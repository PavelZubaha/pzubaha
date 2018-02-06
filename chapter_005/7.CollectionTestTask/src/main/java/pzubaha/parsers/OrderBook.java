package pzubaha.parsers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
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
 * Class represents Order Book.
 * Created 05.01.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class OrderBook {
    private final String id;
    private HashMap<Integer, Order> unsorted = new HashMap<>(32768, 3f);
    private BidAskMap bid = new BidAskMap((o1, o2) -> (-1 * Float.compare(o1, o2)));
    private BidAskMap ask = new BidAskMap(Float::compare);

    /**
     * Constructor
     * @param id id for this OrderBook.
     */
    OrderBook(String id) {
        this.id = id;
    }

    /**
     * This is add order operation.
     * First checks input price and value:
     * When there is no orders in the book with same price and operation,
     * than should check opposite-operations orders for matching.
     * @param operation incoming type operation.
     * @param id incoming id.
     * @param vol incoming volume.
     * @param price incoming price.
     */
    void addOrder(OPERATION operation, int id, int vol, float price) {
        SinglePriceMap map;
        BidAskMap bidAsk;
        bidAsk = operation == OPERATION.BUY ? bid : ask;
        if (!bidAsk.containsKey(price)) {
            vol = bidAsk == bid ? ask.checkIncomingOppositeOrder(price, vol) : bid.checkIncomingOppositeOrder(price, vol);
            map = new SinglePriceMap(64, 2f);
        } else {
            map = bidAsk.get(price);
        }
        if (vol != 0) {
            bidAsk.put(price, map);
            Order order = new Order(operation, id, vol, price);
            map.put(id, order);
            unsorted.put(order.id, order);
        }
    }

    /**
     * This is del order operation.
     * @param id order id for deleting.
     */
    boolean delOrder(int id) {
        boolean result = false;
        Order order = unsorted.remove(id);
        if (order != null) {
            result = true;
            BidAskMap map = order.operation == OPERATION.BUY ? bid : ask;
            SinglePriceMap spm = map.get(order.price);
            spm.remove(id, order);
            if (spm.sum == 0) {
                map.remove(order.price, spm);
            }
        }
        return result;
    }

    /**
     * Enum for different operation type.
     */
    enum OPERATION {
        BUY, SELL
    }

    /**
     * Class represents hash map of same price orders mapping by id's.
     */
    private class SinglePriceMap extends LinkedHashMap<Integer, Order> {
        int sum = 0;

        SinglePriceMap(int initCap, float loadFactor) {
            super(initCap, loadFactor, false);
        }

        @Override
        public Order put(Integer key, Order value) {
            sum += value.vol;
            return super.put(key, value);
        }
        @Override
        public boolean remove(Object key, Object value) {
            boolean result = super.remove(key, value);
            if (result) {
                sum -= ((Order) value).vol;
            }
            return result;
        }
    }

    /**
     * Class represents Order.
     */
    private class Order {
        final int id;
        int vol;
        final float price;
        final OPERATION operation;
        Order(OPERATION operation, int id, int vol, float price) {
            this.operation = operation;
            this.id = id;
            this.vol = vol;
            this.price = price;
        }
    }

    /**
     * Method for getting StringBuilder which represents this OrderBook.
     * @return StringBuilder representation.
     */
    public StringBuilder print() {
        StringBuilder result = new StringBuilder(512);
        result.append(String.format("%nBook: %s%n%-12s%s%12s%n", id, "ASK", "price", "BID"));
        Iterator<Map.Entry<Float, SinglePriceMap>> bidIt = bid.entrySet().iterator();
        Iterator<Map.Entry<Float, SinglePriceMap>> askIt = ask.descendingMap().entrySet().iterator();
        Map.Entry<Float, SinglePriceMap> entry;
        while (askIt.hasNext()) {
            entry = askIt.next();
            result.append(String.format("%-10d%7.2f%n", entry.getValue().sum, entry.getKey()));
        }
        while (bidIt.hasNext()) {
            entry = bidIt.next();
            result.append(String.format("%17.2f%12d%n", entry.getKey(), entry.getValue().sum));
        }
        return result;
    }

    /**
     * Class represents bid or ask queries.
     * Bid ladder is sorted from the highest bid price at the top to lowest price at the end.
     * Ask ladder – visa versa, sorted from lowest at the top to highest price at the end.
     */
    private class BidAskMap extends TreeMap<Float, SinglePriceMap> {
        BidAskMap(Comparator<Float> comparator) {
            super(comparator);
        }

        /**
         * Check and correcting incoming and present order volumes.
         * @param oppositePrice incoming order price.
         * @param oppositeVol icoming order volume.
         * @return checked incoming order vol.
         */
        int checkIncomingOppositeOrder(float oppositePrice, int oppositeVol) {
            Iterator<Map.Entry<Float, SinglePriceMap>> priceIt = entrySet().iterator();
            Iterator<Map.Entry<Integer, Order>> orderIt;
            Order order;
            Map.Entry<Float, SinglePriceMap> mapEntry;
            while (priceIt.hasNext() && oppositeVol != 0) {
                mapEntry = priceIt.next();
                if (super.comparator().compare(oppositePrice, mapEntry.getKey()) < 0) {
                    break;
                }
                orderIt = mapEntry.getValue().entrySet().iterator();
                while (orderIt.hasNext() && oppositeVol != 0) {
                    order = orderIt.next().getValue();
                    if (order.vol <= oppositeVol) {
                        oppositeVol -= order.vol;
                        unsorted.remove(order.id);
                        orderIt.remove();
                        mapEntry.getValue().sum -= order.vol;
                    } else {
                        order.vol -= oppositeVol;
                        mapEntry.getValue().sum -= oppositeVol;
                        oppositeVol = 0;
                    }
                    if (mapEntry.getValue().sum == 0) {
                        priceIt.remove();
                        break;
                    }
                }
            }
            return oppositeVol;
        }
    }
}
