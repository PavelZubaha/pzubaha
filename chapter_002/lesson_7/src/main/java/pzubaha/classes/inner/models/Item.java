package pzubaha.classes.inner.models;
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
	private String[] comments = new String[4];

	/**
	 * current number of comment.
	 */
	private int currentCommentNumber = 0;
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
		this.comments[currentCommentNumber++] = comment;
		if (comments.length == currentCommentNumber) {
			String[] temp = new String[comments.length * 2];
			System.arraycopy(this.comments, 0, temp, 0, comments.length);
			this.comments = temp;
		}
	}
	/**
	 * method for showing comments.
	 * @return comments as String instance.
	 */
	public String showItemComments() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		if (currentCommentNumber > 0) {
			String separator = String.format("%n----------------------------------%n");
			for (int i = 0; i < currentCommentNumber; i++) {
				builder.append(String.format("%4d%-4s%s%s", i + 1, ".", comments[i], separator));
			}
		}
		return builder.toString();
	}
	/**
	 * method for getting comments.
	 * @return comments as String[] instance. When there is no comments then returned array has 0 length.
	 */
	public String[] getComments() {
		String[] result = new String[currentCommentNumber];
		if (currentCommentNumber != 0) {
			System.arraycopy(this.comments, 0, result, 0, currentCommentNumber);
		}
		return result;
	}
}