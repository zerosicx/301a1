public class MyMinHeap {

    private final int MIN_SIZE = 4;
    private String[] heapArray;
    private int next;

    public MyMinHeap(int size) {
        if (size < MIN_SIZE) {
            size = MIN_SIZE;
        }

        heapArray = new String[size + 1];
        next = 1;
    }

    public void insert(String s) {
        heapArray[next] = s;
        upheap(next);
        this.next++;
    }

    public void upheap(int pos) {
        System.out.println("Upheaping");

        int child = pos;

        while (child > 1) {
            int parent = child / 2;
            if (heapArray[child].compareTo(heapArray[parent]) <= -1) { // If child is pre
                swap(child, parent);
            }

            child = parent;
        }
    }

    public void remove() {
        int root = 1;
        int lastLeafIndex = next - 1;

        if (next == 1) { // if the heap is empty, do nothing
            return;
        }

        if (root == lastLeafIndex) {
            heapArray[lastLeafIndex] = null; // Set empty string
        } else { // Otherwise, swap
            swap(root, lastLeafIndex);
            heapArray[lastLeafIndex] = null;
            next--;
        }

        downheap();
    }

    public void downheap() {
        // Get the root index - set it as parent
        // While the parent is less than or equal to next//2
        System.out.println("Downheaping");
        int parent = 1;
        int smallest = 1;

        while (parent < next) {
            int left = parent * 2;
            int right = parent * 2 + 1;

            if (left < next && heapArray[left].compareTo(heapArray[parent]) <= -1) {
                smallest = left;
            }

            if (right < next && heapArray[right].compareTo(heapArray[smallest]) <= -1) {
                smallest = right;
            }

            if (parent != smallest) {
                swap(parent, smallest);
                parent = smallest;
            } else {
                break; // If parent is the smallest, we are in heap order. No need to
                // continue the loop.
            }

        }

    }

    public void replace(String s) {
        // Print the top node if not null
        System.out.println(heapArray[1]);

        // Remove the top
        remove();
        printHeap();
        insert(s);
    }

    public void printHeap() {
        for (int i = 1; i < next; i++) {
            System.out.println(
                    heapArray[i] + " | leftChild: " + heapArray[i * 2] + " | rightChild: " + heapArray[i * 2 + 1]);
        }
    }

    private void swap(int p1, int p2) {
        String temp = heapArray[p1];
        heapArray[p1] = heapArray[p2];
        heapArray[p2] = temp;
    }

}