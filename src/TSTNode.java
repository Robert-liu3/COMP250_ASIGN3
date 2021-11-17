// add your imports here

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child

    // TODO: implement the node class here
    
    TSTNode(T element){
        this.element = element;
    }

    TSTNode<T> findMax(){
        return findMax(this);
    }

    TSTNode<T> findMin(){
        return findMin(this);
    }
    public TSTNode<T> findMin(TSTNode<T> root) {
        if (root == null) return null;
        else if (root.left == null) {
            return root;
        }
        else
            return findMin(root.left);
    }
    public TSTNode<T> findMax(TSTNode<T> root) {
        if (root == null) return null;
        else if (root.right == null) {
            return root;
        }
        else
            return findMax(root.right);
    }

    int height(){
        int maxHeight = 0;
        maxHeight = this.heightTwo();
        if (maxHeight > 0 ){
            maxHeight -= 1;
        }
        return maxHeight;
    }
    int heightTwo() {
        int leftHeight = 0;
        int rightHeight = 0;
        int midHeight = 0;
        int maxHeight = 0;
        if (this.left != null) {
            leftHeight = this.left.heightTwo();
        }
        if (this.right != null) {
            rightHeight = this.right.heightTwo();
        }
        if (this.mid != null) {
            midHeight = this.mid.heightTwo();
        }
        maxHeight = max(leftHeight, rightHeight, midHeight);
        return maxHeight+1;
    }

    int max(int valueOne, int valueTwo, int valueThree) {
        int maxNum = -1;
        if (valueOne >= valueTwo) {
            maxNum = valueOne;
        } else if (valueTwo >= valueOne) {
            maxNum = valueTwo;
        }
        if (valueThree >= maxNum) {
            maxNum = valueThree;
        }
        return maxNum;
    }
    // add your own helper methods if necessary
}