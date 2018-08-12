package pzubaha.classes.inner.models;
import pzubaha.classes.inner.start.TrackerDB;

import java.sql.Timestamp;
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
	private int id;
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
	private Timestamp create;
	/**
	 * Id of user.
	 */
	private int userId;
	/**
	 * Status id.
	 */
	private int statId;
	/**
	 * Category_id.
	 */
	private int catId;
	/**
	 * Comments.
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
	public Item(String name, String description, int userId, int catId) {
		this.name = name;
		this.description = description;
		this.create = TrackerDB.getCurrentTimeStamp();
		this.userId = userId;
		this.catId = catId;
		this.setStatId(1);
	}

	public Item(int id, String name, String description, Timestamp create, int userId, int statId, int catId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.create = create;
		this.userId = userId;
		this.statId = statId;
		this.catId = catId;
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
	public Timestamp getCreate() {
		return this.create;
	}
	/**
	 * method for getting id of item example.
	 * @return id.
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * method for setting id of item example.
	 * @param id - id needed to set.
	 */
	public void setId(int id) {
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

	public int getStatId() {
		return statId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setStatId(int statId) {
		this.statId = statId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Item{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", create=").append(create);
		sb.append(", user_id=").append(userId);
		sb.append(", stat_id=").append(statId);
		sb.append(", cat_id=").append(catId);
		sb.append(", comments=").append(comments);
		sb.append('}');
		return sb.toString();
	}
}