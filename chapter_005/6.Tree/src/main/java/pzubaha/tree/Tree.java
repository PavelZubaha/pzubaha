package pzubaha.tree;

import java.util.*;

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
public class Tree<T extends Comparable<T>> implements SimpleTree<T> {
    /**
     * Variable for root node.
     */
    private Node<T> root;

    public Tree(T value) {
        this.root = new Node<>(value);
    }

    /**
     * Add method for elements.
     *
     * @param parent parent.
     * @param child  child.
     * @return value true if value was added.
     */
    @Override
    public boolean add(T parent, T child) {
        if (root == null) {
            return false;
        }
        Optional<Node<T>> parentOptional = findBy(parent);
        boolean result = parentOptional.isPresent() && !findBy(child).isPresent();
        if (result) {
            parentOptional.get().add(new Node<>(child));
        }
        return result;
    }

    /**
     * For finding Node by stored value,
     *
     * @param value value.
     * @return Node wrapped by java.util.Optional.
     */
    @Override
    public Optional<Node<T>> findBy(T value) {
        Optional<Node<T>> rsl = Optional.empty();
        Queue<Node<T>> data = new LinkedList<>();
        Node<T> el;
        data.offer(this.root);
        while (!data.isEmpty()) {
            el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<T> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Iterator for this tree.
     * @return iterator.
     */
    @Override
    public Iterator<T> iterator() {
        class TreeIterator implements Iterator<T> {
            private Queue<Node<T>> data;
            private Node<T> el;
            private TreeIterator() {
                data = new LinkedList<>();
                data.offer(root);
            }
            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("There is no more elements");
                }
                el = data.poll();
                for (Node<T> child : el.leaves()) {
                    data.offer(child);
                }
                return el.getValue();
            }
        }
        return new TreeIterator();
    }
    /**
     * Checking is this tree is binary.
     * @return true if the tree is  binary(each node has <= 2 child), false otherwise.
     */
    public boolean isBinary() {
        return root.isBinary();
    }
}


