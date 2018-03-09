package pzubaha.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Chapter_006. Multithreading.
 * Monitor, Synchronized.
 * Required to make a class UserStorage
 * with methods: boolean add (User user), boolean update(User user),
 * boolean delete(User user), transfer(int fromId, int toId, int amount).
 * <p>
 * Contains solution of task 1104.
 * Class UserStorage.
 * Created 04.03.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
@ThreadSafe
public class UserStorage {

    /**
     * Storage fore users, by id.
     */
    @GuardedBy("this")
    private Map<Integer, User> userMap = new HashMap<>();

    /**
     * Add to storage method.
     * @param user user for store.
     * @return true if user added, false otherwise(if there is some user had mapped before).
     */
    public synchronized boolean add(User user) {
        boolean result = false;
        if (!userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * Update for stored user.
     * @param user user for updating.
     * @return true if user updated, false otherwise.
     */
    public synchronized boolean update(User user) {
        return userMap.replace(user.getId(), userMap.get(user.getId()), user);
    }

    /**
     * Delete method
     * @param user user for delete.
     * @return true if this user had deleted, otherwise false.
     */
    public synchronized boolean delete(User user) {
        return userMap.remove(user.getId(), user);
    }

    /**
     * transfer some amount from user to another one.
     * @param fromId user id - source of transfer.
     * @param toId user id - endpoint of transfer.
     * @param amount amount of transfer.
     * @return true if transfer had been succeed, false otherwise.
     */
    public boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        synchronized (this) {
            User fromUser = userMap.get(fromId);
            User toUser = userMap.get(toId);
            if (fromUser != null && toUser != null && fromUser.getAmount() >= amount) {
                fromUser.setAmount(fromUser.getAmount() - amount);
                toUser.setAmount(toUser.getAmount() + amount);
                result = true;
            }
        }
        return result;
    }

}
class User {
    private final int id;
    private int amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return this.getId() == user.getId() && this.getAmount() == user.getAmount();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    int getId() {
        return id;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }
}
