import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Long value;
    private Tree left = null;
    private Tree right = null;

    public Tree(Long value) {
        this.value = value;
    }

    public Tree add(Long num) {
        if (num > this.value) {
            if (this.right != null) {
                return this.right.add(num);
            } else {
                this.right = new Tree(num);
                return this.right;
            }
        } else {
            if (this.left != null) {
                return this.left.add(num);
            } else {
                this.left = new Tree(num);
                return this.left;
            }
        }
    }

    public int getNbChildren() {
        if (this.right != null && this.left != null)
            return 2;
        if (this.right == null && this.left != null ||
                this.right != null && this.left == null
        )
            return 1;
        return 0;
    }

    public Tree getInOrderSuccNode() {
        Tree cursor = this;
        Tree previous = this;

        if (cursor.right == null) {
            return cursor;
        }
        while (true) {
            if (cursor.right != null) {
                previous = cursor;
                cursor = cursor.right;
            } else {
                break;
            }
        }
        Tree temp = new Tree(previous.right.value);
        previous.right = previous.right.remove(previous.right.value);
        return temp;
    }

    public Tree getInOrderPredNode() {
        Tree cursor = this;
        Tree previous = this;

        if (cursor.left == null) {
            return cursor;
        }
        while (true) {
            if (cursor.left != null) {
                previous = cursor;
                cursor = cursor.left;
            } else {
                break;
            }
        }
        Tree temp = new Tree(previous.left.value);
        previous.left = previous.left.remove(previous.left.value);
        return temp;
    }
    public Tree remove(long num){
        Tree save = this;
        Tree cursor = this;
        Tree previous = this;
        if (previous.value == num && previous.getNbChildren() == 0) {
            return null;
        }
        while (cursor != null && cursor.value != num) {

            if (num > cursor.value) {
                previous = cursor;
                cursor = cursor.right;
            } else {
                previous = cursor;
                cursor = cursor.left;
            }
        }

        if (cursor == null) {
            return save;
        }

        Tree inOrderElement = null;

        if (cursor.getNbChildren() >= 1) {
            if (cursor.left == null) {
                if (cursor.right.left == null) {
                    cursor.value = cursor.right.value;
                    cursor.right = cursor.right.right;
                } else {
                    inOrderElement = cursor.right.getInOrderPredNode();
                    cursor.value = inOrderElement.value;
                }
            } else {
                if (cursor.left.right == null) {
                    cursor.value = cursor.left.value;
                    cursor.left = cursor.left.left;
                } else {
                    inOrderElement = cursor.left.getInOrderSuccNode();
                    cursor.value = inOrderElement.value;
                }
            }

        } else {
            if (previous.right != null && previous.right.value == num) {
                previous.right = null;
            } else {
                previous.left = null;
            }
        }


        return save;
    }

    public List<Long> snapshot() {
         List<Long> arr = new ArrayList<>();
         getNextChild(this, arr);
         return arr;
    }

    public void getNextChild(Tree tree, List<Long> arr) {
        if (tree.left == null && tree.right == null) {
            arr.add(tree.value);
            return;
        }
        if (tree.left != null) {
            getNextChild(tree.left, arr);
        }
        arr.add(tree.value);
        if (tree.right != null) {
            getNextChild(tree.right, arr);
        }
    }

}
