package pzubaha.list;

/**
 * Chapter_005. Collection. Pro.
 * List.
 * Find loop in the list.
 * <p>
 * Contains solution of task 161.
 * Class represents simple linked list.
 * Created 10.11.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
class LinkedListLoop {

    /**
     * Head of Node sequence.
     */
    private Node head;

    /**
     * Static method for crating Node instance.
     * @return reference to just created Node.
     */
    Node makeNode() {
        return new Node();
    }

    /**
     * Class Node represents element of simple linked list.
     */
    class Node {
        Node next;
        void connect(Node nextNode) {
            this.next = nextNode;
        }
    }

    /**
     * Method for set head.
     * @param head Node that will be head.
     */
    void setHead(Node head) {
        this.head = head;
    }

    /**
     * Method try this list contains loop or not.
     * @return condition.
     */
    boolean isLoop() {
        if (head == null) {
            return false;
        }
        boolean cond = false;
        Node slow = head, quick = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) {
                cond = true;
                break;
            }
        }
        return cond;
    }
}
