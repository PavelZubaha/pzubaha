package pzubaha.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Special class for store date in Tree.
 * @param <T> parametrized ype
 */
class Node<T> {
    private final List<Node<T>> children = new ArrayList<>();
    private final T value;
    Node(final T value) {
        this.value = value;
    }

    /**
     * Add child to this node.
     * @param child specified child element of this node.
     */
    void add(Node<T> child) {
        this.children.add(child);
    }

    List<Node<T>> leaves() {
        return this.children;
    }

    /**
     * Getter for value.
     * @return stored value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Recursive adding all elements to list.
     * @param nodes list to which all elements will be added.
     */
    public void addAllToList(List<Node<T>> nodes) {
        nodes.add(this);
        for (Node<T> child : this.leaves()) {
            child.addAllToList(nodes);
        }
    }

    /**
     * Equals method using value.
     * @param that another value.
     * @return true if this value equals that one.
     */
    public boolean eqValue(T that) {
        return this.value.equals(that);
    }
}
