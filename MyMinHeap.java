public class MyMinHeap {

    private String[] heapArray;
    public int next; // So it can be accessed from the outside

    /**
     * Initialisez a MyMinHeap object with a specified size. +1 is added as the
     * initial index 0 is not used.
     * 
     * @param size
     */
    public MyMinHeap(int size) {
        heapArray = new String[size + 1];
        next = 1;
    }

    /**
     * Inserts a value to the end of the MyMinHeap object. Upheaps to ensure it is
     * in the correct location to maintain partial ordering.
     * 
     * @param s
     */
    public void insert(String s) {
        heapArray[next] = s;
        upheap(next);
        next++;
    }

    /**
     * Iteratively swap positions of two nodes if a node is displaced with respect
     * to its parent.
     * 
     * @param pos
     */
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

    /**
     * Remove the root node by swapping it to the last value and setting the last
     * value to null. Do nothing if the heap is empty. Downheap to maintain partial
     * ordering.
     */
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

    /**
     * Recursively swap a parent with its smallest child node if it is displaced
     * with respect to its children. Continue until the node is in the correct
     * position.
     * 
     * @param parent
     */
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

    /**
     * Replace the top node of a MyMinHeap object by removing and inserting the
     * provided value s. Remove and insert automatically deal with the heap
     * ordering.
     */
    public void replace(String s) {
        // Remove the top
        remove();
        insert(s);
    }

    /**
     * Prints all nodes and their left/right children
     */
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

    /**
     * Returns the value of the root node
     * 
     * @return
     */
    public String peek() {
        if (heapArray[1] == null) {
            System.err.println("HEAP IS EMPTY");
            return null;
        }

        return heapArray[1];
    }

    /**
     * Inserts all strings in arr if they are not null. Ensures we are not inserting
     * more than we need, and prevents null values from polluting the heap.
     * 
     * @param arr
     */
    public void load(String[] arr) {
        for (String s : arr) {
            if (s != null) {
                insert(s);
            }
        }
    }

    /**
     * A helper function to shorted the scope of the minheap temporarily.
     */
    public void shortenScope() {
        // Swap the first item and last item, then decrease count and reheap.
        swap(1, next - 1);
        next--;
        downheap(1);
    }

    /**
     * A helper function to swap items in the provided indeces.
     */
    private void swap(int p1, int p2) {
        String temp = heapArray[p1];
        heapArray[p1] = heapArray[p2];
        heapArray[p2] = temp;
    }

    /**
     * A helper function that restores the heap size and reheaps to put all nodes
     * in correct minheap order.
     */
    public void restoreScope() {
        next = heapArray.length;
        reheap();
    }

    /**
     * Returns the value of next
     * 
     * @return int
     */
    public int getNext() {
        return this.next;
    }

    public void reheap() {
        int i = next - 1;
        // First parent
        while (i > 1) {
            upheap(i);
            i--;
        }
    }

    /**
     * A helper function to print everything in the MyMinHeap object, regardless of
     * if it is in the scope or not. Also prints value of "next" so we can tell what
     * is in scope.
     */
    public void printEverything() {
        for (String s : heapArray) {
            System.out.print(s + " ");
        }
        System.out.println("Next: " + next);
    }

}