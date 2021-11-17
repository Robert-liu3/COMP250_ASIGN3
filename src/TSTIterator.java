import java.util.Comparator;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;
// add your imports here

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
     private Iterator nodeIterator;
    // TODO: implement the iterator class here
    // add your own helper methods if necessary
    TSTIterator(TST tree) {
        LinkedList nodeList = new LinkedList<T>();
        addToList(tree.root, nodeList);
        nodeList.sort(Comparator.naturalOrder());
        nodeIterator = nodeList.iterator();

    }
    public void addToList(TSTNode root, LinkedList nodeList) {
        if (root == null) {
            return;
        }
        nodeList.add(root.getElement());
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
        return nodeIterator.hasNext();
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
        T nextObject = (T) nodeIterator.next();
        return nextObject;
    }
}