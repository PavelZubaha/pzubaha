package pzubaha.userconvert;

/**
 * Chapter_003. Collections. Lite.
 * Generic.
 *
 * Contains solution of task 10093.
 * Class for Converting List to Map.
 *
 * Created 27.09.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class User {
    /**
     * user id.
     */
    private int iD = 0;
    /**
     * user city.
     */
    private String city;
    /**
     * user name.
     */
    private String name;

    /**
     * Constructor.
     * @param iD user id.
     * @param city user city.
     * @param name user name.
     */
    public User(String name, String city, int iD) {
        this.iD = iD;
        this.city = city;
        this.name = name;
    }

    /**
     * getter id.
     * @return id.
     */
    public int getiD() {
        return iD;
    }

    /**
     * getter city.
     * @return user city.
     */
    public String getCity() {
        return city;
    }

    /**
     * getter name.
     * @return user name.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (this.iD == ((User) obj).getiD() && this.name.equals(((User) obj))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return iD;
    }
}
