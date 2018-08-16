package pzubaha.generic;

/**
 * Chapter_005. Collection. Pro.
 * Generic.
 * <p>
 * Contains solution of task 157.
 * Abstract class for different stores.
 * Created 25.10.2017.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * Store.
     */
    private SimpleArray<T> store = new SimpleArray<>(30);

    /**
     * Method for adding elements.
     * @param model model to add.
     */
    @Override
    public void add(T model) {
        store.add(model);
    }

    /**
     * Method for updating elements.
     * @param model model to update.
     */
    @Override
    public boolean update(T model, String id) {
        boolean result = false;
        int index = getIndexById(id);
        if (index != -1) {
            result = store.update(index, model);
        }
        return result;
    }

    /**
     * delete method for elements of store.
     * @param id id of element to delete.
     * @return true if element deleted, false if there is no the element with the id.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = getIndexById(id);
        if (index != -1) {
            result = store.delete(index);
        }
        return result;
    }

    /**
     * Get method for getting element from store.
     * @param id id of element.
     * @return index element or -1 if element doesn't contains in the array.
     */
    private int getIndexById(String id) {
        int result = -1;
        if (id != null) {
            final int length = store.length();
            for (int i = 0; i != length; i++) {
                T model = store.get(i);
                if (model != null && model.getId().equals(id)) {
                    result = i;
                }
            }
        }
        return result;
    }

    /**
     * Get T by id.
     * @param id id of element.
     * @return element by id or null if there are no elements with the id.
     */
    public T getById(String id) {
       int index = getIndexById(id);
       return  index != -1 ? store.get(index) : null;
    }
}
