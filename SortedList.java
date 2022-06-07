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
            System.out.println("Tableau vide");
            return;
        }
        int start = 0;
        if (this.tree == null) {
           this.tree = new Tree(values.get(0));
           start = 1;
        }
        for (int i = start; i < values.size(); i++) {
            this.tree.add(values.get(i));
        }
    }

    // DELETE
    public void remove(List<Long> values) {
        for (int i = 0; i < values.size(); i++) {
            if (this.tree == null) {
                break;
            }
            this.tree = this.tree.remove(values.get(i));
        }
    }

    // GET
    public List<Long> snapshot() {
        if (this.tree == null)
            return new ArrayList<>();
        return this.tree.snapshot();
    }
}