package pzubaha.userconvert;

import java.util.HashMap;
import java.util.List;

/**
 * Chapter_003. Collections. Lite.
 * Generic.
 *
 * Contains solution of task 10093.
 * Class for Converting List to Map.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 27.09.17
 * @version 1
 */
public class UserConvert {
    /**
     * Convert List to HashMap.
     * @param list original list.
     * @return HashMap with all elements from original list.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> resultMap = new HashMap<>(16);
        for (User user : list) {
            resultMap.put(user.getiD(), user);
        }
        return resultMap;
    }
}