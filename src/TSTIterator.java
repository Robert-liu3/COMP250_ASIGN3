import java.util.ArrayList;
import java.util.Iterator;
// add your imports here

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
    // TODO: implement the iterator class here
    // add your own helper methods if necessary
    TSTIterator(TST tree) {
        ArrayList elementList = new ArrayList();
        elementList.add(tree.root);
    }
    public void addToList(TSTNode root, ArrayList nodeList) {
        if (root == null) {
            return;
        }
        nodeList.add(root);
        if (root.left != null) {
            addToList(root.left, nodeList);
        }
        if (root.mid != null) {
            addToList(root.mid, nodeList);
        }
        if (root.right != null) {
            addToList(root.right, nodeList);
        }
    }
    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws NoSuchElementException
     *         if the iteration has no more elements
     */
    @Override
    public T next() {

        return null;
    }
}