package pzubaha.generic;
/**
 * Chapter_005. Collection. Pro.
 * Generic.
 * <p>
 * Contains solution of task 157
 * Base class.
 * Created 25.10.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public abstract class Base {
    /**
     * Some id of instance.
     */
    private String id;
    /**
     * Constructor for Base.
     * @param id mad
     */
    public Base(String id) {
        this.id = id;
    }
    /**
     * Getter for id.
     * @return id.
     */
    public String getId() {
        return this.id;
    }
    /**
     * Setter for id.
     */
    public void setId(String id) {
        this.id = id;
    }
}
