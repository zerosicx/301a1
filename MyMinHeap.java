import java.util.ArrayList;

class MyMinHeap {

    private ArrayList<String> heapArray;
    private int next;

    public MyMinHeap(int size) {
        heapArray = new ArrayList<String>(size);
        next = 1;
    }

    /** Inserts data to the next position in the heap */
    public void insert(String s) {
        heapArray.set(next, s);
        this.next++;

        // Don't need to upheap as we can assume the CreateRuns will only ever insert
        // the right item
    }

    public void remove() {
        // Swap with bottom element
        String temp = heapArray.get(next - 1); // Get the bottom element
        heapArray.set(0, temp);
        heapArray.set(next - 1, "");
        this.next--;

        // Downheap to put it in the right place

    }

    public void replace() {

    }

    public String peek() {
        return "";
    }

    public void load() {
    }

    public void reheap() {
    }
}