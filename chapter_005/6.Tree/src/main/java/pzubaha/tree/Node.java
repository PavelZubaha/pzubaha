package pzubaha.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Chapter_005. Collection. Pro.
 * 6.Tree. Elementary tree.
 * <p>
 * Contains solution of task 1712.
 * Class represents the simple tree structure.
 * Created 26.12.2017.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 * <T> - parametrized type implementing Comparable.
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

    /**
     * Checking is this tree is binary.
     * @return true if the tree is  binary(each node has <= 2 child), false otherwise.
     */
    public boolean isBinary() {
        boolean result = true;
        if (leaves().size() > 2) {
            result = false;
        } else {
            for (Node node : leaves()) {
                node.isBinary();
            }
        }
        return result;
    }
}
