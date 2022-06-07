package fr.aseure.tp014.ex1;

import java.util.ArrayList;
import java.util.List;

public class Tree implements Cloneable{
    public Long entier;
    public Tree left = null;
    public Tree right = null;
    public Tree parent = null;

    public Tree(Long entier) {
        this.entier = entier;
    }

    public Tree add(Long num) {
        if (num > this.entier) {
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
    // on prend le noeud le plus grand dans le fils gauche du cursor
    public Tree getInOrderSuccNode() throws CloneNotSupportedException {
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
        Tree temp =(Tree) previous.right.clone();
        previous.right = previous.right.remove(previous.right.entier);
        //previous.right = null;
        return temp;
    }

    public Tree getInOrderPredNode() throws CloneNotSupportedException {
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
        Tree temp = (Tree) previous.left.clone();
        previous.left = previous.left.remove(previous.left.entier);
        return temp;
    }
    public Tree remove(long num) throws CloneNotSupportedException {
        Tree save = this;
        Tree cursor = this;
        Tree previous = this;
        if (previous.entier == num && previous.getNbChildren() == 0) {
            return null;
        }
        while (cursor != null && cursor.entier != num) {

            if (num > cursor.entier) {
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
                    cursor.entier = cursor.right.entier;
                    cursor.right = cursor.right.right;
                } else {
                    inOrderElement = cursor.right.getInOrderPredNode();
                    cursor.entier = inOrderElement.entier;
                }
            } else {
                if (cursor.left.right == null) {
                    cursor.entier = cursor.left.entier;
                    cursor.left = cursor.left.left;
                } else {
                    inOrderElement = cursor.left.getInOrderSuccNode();
                    cursor.entier = inOrderElement.entier;
                }
            }

        } else {
            if (previous.right != null && previous.right.entier == num) {
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
            arr.add(tree.entier);
            return;
        }
        if (tree.left != null) {
            getNextChild(tree.left, arr);
        }
        arr.add(tree.entier);
        if (tree.right != null) {
            getNextChild(tree.right, arr);
        }
    }

}
