package pzubaha.threads.threadswitcher;

/**
 * Chapter_006. Multithreading.
 * 6. Control tasks.
 * <p>
 * Contains solution of task 50581.
 * Representation of added int sequence as string.
 * Created 30.04.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StringAsIntHolder {
    /**
     * Storage of chars.
     */
    private StringBuilder holder = new StringBuilder(32);

    /**
     * Default constructor.
     */
    public StringAsIntHolder() {
    }

    /**
     * Method appends specified int value.
     * @param value int value for append.
     */
    public void appendInt(int value) {
        holder.append(value);
    }

    /**
     * Get String representation of StringBuilderHolder.
     * @return string representation of added int sequence.
     */
    public String getString() {
        return holder.toString();
    }
}
