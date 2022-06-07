package fr.aseure.tp014.ex1;

import java.util.ArrayList;
import java.util.List;

public class SortedList {
    private Tree tree;

    SortedList() {
        this.tree = null;
    }

    // PUT
    public void add(List<Long> values) {

        if (values.size() == 0) {
            System.out.println("error le tableau est vide");
            return;
        }
        int start = 0;
        if (this.tree == null) {
           this.tree = new Tree(values.get(0));
           start = 1;
        }
        for (int i = start; i < values.size(); i++) {
            System.out.println(values.get(i));
            this.tree.add(values.get(i));
        }
    }

    // DELETE
    public void remove(List<Long> values) throws CloneNotSupportedException {
        //if (this.tree != null)
        if (values.size() == 0) {
            System.out.println("error le tableau est vide");
            return;
        }
        if (this.tree == null) {
            System.out.println("error tree vide");
            return;
        }
        for (int i = 0; i < values.size(); i++) {
            if (this.tree == null) {
                break;
            }
            this.tree = this.tree.remove(values.get(i));
        }
    }

    // GET
    public List<Long> snapshot() {
        if (this.tree == null) {
            return new ArrayList<>();
        }
        return this.tree.snapshot();
    }
}