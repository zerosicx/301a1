public class MyMinHeap {

    private String[] heapArray;
    public int next; // So it can be accessed from the outside

    public MyMinHeap(int size) {
        heapArray = new String[size + 1];
        next = 1;
    }

    public void insert(String s) {
        heapArray[next] = s;
        upheap(next);
        next++;
    }

    public void upheap(int pos) {
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
            next--;
        } else { // Otherwise, swap
            swap(root, lastLeafIndex);
            heapArray[lastLeafIndex] = null;
            next--;
        }

        downheap(1);
    }

    public void downheap(int parent) {
        // Get the root index - set it as parent
        // While the parent is less than or equal to next//2
        int smallest = parent;

        while (parent < next) {
            int left = parent * 2;
            int right = parent * 2 + 1;

            if (left < next && heapArray[left].compareTo(heapArray[parent]) <= 0) {
                smallest = left;
            }

            if (right < next
                    && heapArray[right].compareTo(heapArray[smallest]) <= -1) {
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
        // Remove the top
        remove();
        insert(s);
    }

    public void printHeap() {

        String leftChild;
        String rightChild;
        for (int i = 1; i < next; i++) {

            if (i * 2 < next) {
                leftChild = heapArray[i * 2];
            } else {
                leftChild = null;
            }

            if (i * 2 + 1 < next) {
                rightChild = heapArray[i * 2 + 1];
            } else {
                rightChild = null;
            }

            System.out.println(
                    heapArray[i] + " | leftChild: " + leftChild + " | rightChild: " + rightChild);
        }
    }

    public String peek() {
        if (heapArray[1] == null) {
            System.err.println("HEAP IS EMPTY");
            return null;
        }

        return heapArray[1];
    }

    public void load(String[] arr) {
        for (String s : arr) {
            if (s != null) {
                insert(s);
            }
        }
    }

    /**
     * A helper function to shorted the scope of the minheap temporarily
     */
    public void shortenScope() {
        // Swap the first item and last item, then decrease count and reheap.
        swap(1, next - 1);
        next--;
        downheap(1);
    }

    /**
     * A helper function to swap items in the provided indeces
     */
    private void swap(int p1, int p2) {
        String temp = heapArray[p1];
        heapArray[p1] = heapArray[p2];
        heapArray[p2] = temp;
    }

    public void restoreScope() {
        next = heapArray.length;
        reheap();
    }

    public int getNext() {
        return this.next;
    }

    public void reheap() {
        int i = next;
        while (i > 1) {
            if (i % 2 == 0)
                i = i / 2;

            downheap(i);
            i--;
        }
    }

    public boolean printRemaining() {
        boolean toReturn = false;
        for (String s : heapArray) {
            if (s != null) {
                System.out.println(s);
                toReturn = true;
            }
        }
        return toReturn;
    }

    // For debugging
    public void printEverything() {
        for (String s : heapArray) {
            System.out.print(s + " ");
        }
    }

    public boolean fixRemainingData() {
        boolean remaining = false;
        int fixNum = heapArray.length;
        String[] toLoad = new String[fixNum];

        for (int i = 0; i < fixNum; i++) { // Add all values
            if (heapArray[i] != null) {
                toLoad[i] = heapArray[i];
                remaining = true;
            }
        }

        clearHeap();
        load(toLoad);
        return remaining;
    }

    public void clearHeap() {
        heapArray = new String[heapArray.length];
        next = 1;
    }

}