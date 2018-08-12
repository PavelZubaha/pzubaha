package pzubaha.classes.inner.models;

/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Category {
    private int catId;
    private String name;

    public Category(int catId, String name) {
        this.catId = catId;
        this.name = name;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
