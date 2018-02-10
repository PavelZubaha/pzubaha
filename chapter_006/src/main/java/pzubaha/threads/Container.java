package pzubaha.threads;

/**
 * Chapter_006. Multithreading.
 * Threads //TODO
 * //TODO comment
 * <p>
 * Contains solution of task //TODO
 * Class
 * Created 09.02.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
class Container<T> {
    private T obj;

    public Container(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
