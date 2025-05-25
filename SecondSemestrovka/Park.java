package SecondSemestrovka;

import java.util.List;

public class Park {
    private final B_Tree structure;
    private int count = 0;

    public Park(int density){
        structure = new B_Tree(density);
    }

    public void book(int id) {
        structure.insert(id);
        count++;
    }

    public void release(int id) {
        structure.delete(id);
        count--;
    }

    public List<Integer> sortedPlaces() {
        return structure.getAllKeysInOrder();
    }

    public boolean isFree(int id) {
        return structure.search(id) == null;
    }

    public int getCount() {
        return count;
    }
}
