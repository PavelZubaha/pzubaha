package pzubaha.classes.inner.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Chapter 2. OOP.
 * Lesson 7. Exceptions.
 *
 * Class Item implements different type of tracker issue/request.
 * Class contains solution of task 789.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @since 29.06.17
 * @version 2
 */
public class Item {
	/**
	 * Unique field for each example of Item.
	*/
	private String id;
	/**
	 * name of each example.
	 */
	private String name;
	/**
	 * description.
	 */
	private String description;
	/**
	 * name of each example.
	 */
	private long create;

	/**
	 * comments.
	 */
	private List<String> comments = new ArrayList<>();

	/**
	 * default constructor for Item.
	 */
	public Item() {
	}
	/**
	 * construtor for Item.
	 * @param name - name.
	 * @param  description - description.
	 */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		this.create = System.currentTimeMillis();
	}
	/**
	 * method for getting name of item example.
	 * @return name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * method for getting description of item example.
	 * @return description.
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * method for getting create of item example.
	 * @return create.
	 */
	public long getCreate() {
		return this.create;
	}
	/**
	 * method for getting id of item example.
	 * @return id.
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * method for setting id of item example.
	 * @param id - id needed to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * method for adding comments.
	 * @param comment - comment text.
	 */
	public void addComment(String comment) {
		this.comments.add(comment);
	}
	/**
	 * method for showing comments.
	 * @return comments as String instance.
	 */
	public String showItemComments() {
        Iterator<String> it = comments.listIterator();
		StringBuilder builder = new StringBuilder("");
		if (comments.size() != 0) {
			String separator = String.format("%n----------------------------------%n");
			for (int i = 0; it.hasNext(); i++) {
				builder.append(String.format("%4d%-4s%s%s", i + 1, ".", it.next(), separator));
			}
		}
		return builder.toString();
	}

	/**
	 * method for getting comments.
	 * @return comments as String[] instance. When there is no comments then returned array has 0 length.
	 */
	public List<String> getComments() {
		List<String> result = new ArrayList<>();
		if (comments.size() != 0) {
			result.addAll(comments);
		}
		return result;
	}

    /**
     * method for deleting comment.
     * @param commentNumber - number of deleting comment.
     */
    public void delComment(int commentNumber) {
        if (commentNumber  <= comments.size() && commentNumber > 0) {
            comments.remove(commentNumber - 1);
        }
    }
}