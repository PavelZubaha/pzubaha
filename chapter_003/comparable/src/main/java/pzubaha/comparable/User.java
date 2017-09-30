package pzubaha.comparable;

/**
 * Chapter_003. Collections. Lite.
 * Generic.
 * <p>
 * Contains solution of task 10034.
 * Class for Converting List to Map.
 * Created 27.09.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class User implements Comparable {
    /**
     * User name.
     */
    private String name;
    /**
     * User age.
     */
    private int age;

    /**
     * Constructor.
     * @param name User name.
     * @param age User age.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Implementation of required by Comparable interface method compareTo.
     * Method compare Users.
     * @param o User to compare.
     * @return negative integer, zero, or a positive integer as this User is less than,
     * equal to, or greater than the specified User.
     */
    @Override
    public int compareTo(Object o) {
        int result;
        if (this == o) {
            result = 0;
        } else {
            result = this.age - ((User) o).getAge();
            if (result == 0) {
                result = this.name.compareTo(((User) o).getName());
            }
        }
        return result;
    }

    /**
     * Getter for name.
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for age.
     * @return int age.
     */
    public int getAge() {
        return age;
    }

    /**
     * method for representing User instances as String.
     * @return String instance.
     */
    @Override
    public String toString() {

        return String.format("{User name: %s, age %s}", this.name, this.age);
    }
}
