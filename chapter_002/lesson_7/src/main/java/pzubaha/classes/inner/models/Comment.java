package pzubaha.classes.inner.models;

public class Comment {


    private int commentId;
    private int itemId;
    private String commentBody;

    public Comment(int commentId, int itemId, String commentBody) {
        this.commentId = commentId;
        this.itemId = itemId;
        this.commentBody = commentBody;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    @Override
    public String toString() {
        return commentBody;
    }
}
