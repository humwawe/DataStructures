package hum.set;

import hum.tree.bst.BST;

/**
 * @author hum
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<E>();
    }

    public void add(E e) {
        bst.add(e);
    }

    public boolean contains(E e) {
        return bst.contains(e);
    }

    public void remove(E e) {
        bst.remove(e);
    }

    public int getSize() {
        return bst.size();
    }

    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
