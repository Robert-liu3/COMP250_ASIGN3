import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TST<T extends Comparable<T>> implements Iterable<T>{
    // root node of the tree
    TSTNode<T> root;

    // constructor
    public TST() {
        this.root = null;
    }

    public TSTNode<T> getRoot() {
        return root;
    }

//    class TreeNode<T> {
//        T element;
//
//        ArrayList<TreeNode> children;
//
//        TreeNode<T> parent;
//    }

    public void insert(T element){
        this.root = add(root, element);
    }

    public TSTNode<T> add(TSTNode<T> root, T key) {
        if (root == null) {
            root = new TSTNode<T>(key);
        }
        else if (key.compareTo(root.element) == 0) {
            root.mid = add(root.mid, key);
        }
        else if (key.compareTo(root.element) < 0) {
            root.left = add(root.left, key);
        }
        else if (key.compareTo(root.element) > 0) {
            root.right = add(root.right, key);
        }
        return root;
    }

    public boolean containPart2(TSTNode<T> root, T key) {
        boolean contain = false;
        if (root == null) {
            contain = false;
        }
        else if (key.compareTo(root.element) == 0) {
            contain = true;
        }
        else if (key.compareTo(root.element) < 0) {
            contain = containPart2(root.left, key);
        }
        else if (key.compareTo(root.element) > 0) {
            contain = containPart2(root.right, key);
        }
        return contain;
    }

    public void remove(T element){
        //if there is a middle case, remove middle case
        //if there is a right case, replace it with the right node
        //if there is a left case, replace it with the left node
        root = remove(root, element);
    }

    public TSTNode<T> remove(TSTNode<T> node, T element) {
        TSTNode<T> tmp = null;
        if (node == null) {
            return node;
        }
        else if (element.compareTo(node.getElement()) < 0) {
            node.left = remove(node.left, element);
        }
        else if (element.compareTo(node.getElement()) > 0) {
            node.right = remove(node.right, element);
        }
        else if (node.mid != null && element.compareTo(node.mid.getElement()) == 0) {
            node.mid = remove(node.mid, element);
        }
        else {
            if (node.left == null && node.mid == null && node.right == null) {
                return null;
            }
            else if (node.left == null && node.mid == null) {
                node = node.right;
            }
            else if (node.right == null && node.mid == null) {
                node = node.left;
            }
            else if (node.right == null && node.left == null) {
                node = node.mid;
            }
            else if (node.mid == null) {
                tmp = node.findMax();
                remove(node.findMax(), node.findMax().getElement());
                node = node.findMax();
            }
        }
        return node;
    }

    public boolean contains(T element){

        boolean contain = containPart2(root, element);
        return contain;
    }

    public void rebalance(){
        ArrayList<T> list = new ArrayList<T>();
        //TST tree = new TST();
        inorder(this.root, list);
        //tree.root = new TSTNode<T>(null);
        root = null;
        root = partition(list);
        //this.root = tree.root;
    }
    public TSTNode<T> partition(List<T> list) {
        ArrayList<T> leftList = new ArrayList<T>();
        ArrayList<T> rightList = new ArrayList<T>();
        ArrayList<T> midList = new ArrayList<T>();
        int midIndex = list.size()/2;


        TSTNode<T> node = new TSTNode<>(list.get(midIndex));


        leftList = new ArrayList<>(list.subList(0, midIndex ));
        rightList = new ArrayList<>(list.subList(midIndex+1, list.size()));


        while(leftList.size() != 0 && leftList.get(leftList.size()-1) == node.getElement()) {
            midList.add(node.getElement());
            leftList.remove(leftList.size()-1);
        }
        while(rightList.size() != 0 && rightList.get(0) == node.getElement()) {
            midList.add(node.getElement());
            rightList.remove(node.getElement());
        }


        if (leftList.size() != 0 ) if (leftList.get(leftList.size()/2).compareTo(list.get(midIndex)) < 0) {
            node.left = partition(leftList);
        }
        if (rightList.size() != 0) if (rightList.get(rightList.size()/2).compareTo(list.get(midIndex)) > 0) {
            node.right = partition(rightList);
        }
        if (midList.size() != 0) {
            node.mid = partition(midList);
        }
        return node;
    }
    public void inorder(TSTNode<T> root, List<T> nodeList) {
        if (root != null) {
            inorder(root.left, nodeList);
            nodeList.add(root.getElement());
            inorder(root.mid, nodeList);
            inorder(root.right, nodeList);
        }
    }
    // add your own helper methods if necessary

    
    /**
     * Caculate the height of the tree.
     * You need to implement the height() method in the TSTNode class.
     *
     * @return -1 if the tree is empty otherwise the height of the root node
     */
    public int height(){
        if (this.root == null)
            return -1;
        return this.root.height();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        TSTIterator iterator = new TSTIterator(this);
        return iterator;
    }

    // --------------------PROVIDED METHODS--------------------
    // The code below is provided to you as a simple way to visualize the tree
    // This string representation of the tree mimics the 'tree' command in unix
    // with the first child being the left child, the second being the middle child, and the last being the right child.
    // The left child is connect by ~~, the middle child by -- and the right child by __.
    // e.g. consider the following tree
    //               5
    //            /  |  \
    //         2     5    9
    //                   /
    //                  8
    // the tree will be printed as
    // 5
    // |~~ 2
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |-- 5
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |__ 9
    //     |~~ 8
    //     |   |~~ null
    //     |   |-- null
    //     |   |__ null
    //     |-- null
    //     |__ null
    @Override
    public String toString() {
        if (this.root == null)
            return "empty tree";
        // creates a buffer of 100 characters for the string representation
        StringBuilder buffer = new StringBuilder(100);
        // build the string
        stringfy(buffer, this.root,"", "");
        return buffer.toString();
    }

    /**
     * Build a string representation of the tertiary tree.
     * @param buffer String buffer
     * @param node Root node
     * @param nodePrefix The string prefix to add before the node's data (connection line from the parent)
     * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
     */
    private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
        buffer.append(nodePrefix);
        buffer.append(node.element);
        buffer.append('\n');
        if (node.left != null)
            stringfy(buffer, node.left,childrenPrefix + "|~~ ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|~~ null\n");
        if (node.mid != null)
            stringfy(buffer, node.mid,childrenPrefix + "|-- ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|-- null\n");
        if (node.right != null)
            stringfy(buffer, node.right,childrenPrefix + "|__ ", childrenPrefix + "    ");
        else
            buffer.append(childrenPrefix + "|__ null\n");
    }

    /**
     * Print out the tree as a list using an enhanced for loop.
     * Since the Iterator performs an inorder traversal, the printed list will also be inorder.
     */
    public void inorderPrintAsList(){
        String buffer = "[";
        for (T element: this) {
            buffer += element + ", ";
        }
        int len = buffer.length();
        if (len > 1)
            buffer = buffer.substring(0,len-2);
        buffer += "]";
        System.out.println(buffer);
    }
}