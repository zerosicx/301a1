public class NodeMinHeap {

    private Node[] nodeHeap;
    private int next;

    public NodeMinHeap(int fileNum) {
        // Create a minheap according to number of files open
        nodeHeap = new Node[fileNum + 1]; // 0 index not used'
        next = 1;
    }

    public Node peek() {
        if (nodeHeap[1] == null) {
            System.err.println("HEAP IS EMPTY");
            return null;
        }

        return nodeHeap[1];
    }

    public void insert(Node n) {
        nodeHeap[next] = n;
        upheap(next);
        next++;
    }

    public void upheap(int pos) {
        int child = pos;

        while (child > 1) {
            int parent = child / 2;
            if (nodeHeap[child].val.compareTo(nodeHeap[parent].val) <= -1) { // If child is pre
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
            nodeHeap[lastLeafIndex] = null; // Set empty string
            next--;
        } else { // Otherwise, swap
            swap(root, lastLeafIndex);
            nodeHeap[lastLeafIndex] = null;
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

            if (left < next && nodeHeap[left].val.compareTo(nodeHeap[parent].val) <= 0) {
                smallest = left;
            }

            if (right < next
                    && nodeHeap[right].val.compareTo(nodeHeap[smallest].val) <= -1) {
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
    public void replace(Node n) {
        // Remove the top
        remove();
        insert(n);
    }

    /**
     * Inserts all strings in arr if they are not null. Ensures we are not inserting
     * more than we need, and prevents null values from polluting the heap.
     * 
     * @param arr
     */
    public void load(Node[] arr) {
        for (Node n : arr) {
            if (n.val != null) {
                insert(n);
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
     * A helper function that restores the heap size and reheaps to put all nodes
     * in correct minheap order.
     */
    public void restoreScope() {
        next = nodeHeap.length;
        reheap();
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
     * A helper function to swap items in the provided indeces.
     */
    private void swap(int p1, int p2) {
        Node temp = nodeHeap[p1];
        nodeHeap[p1] = nodeHeap[p2];
        nodeHeap[p2] = temp;
    }

    /**
     * A helper function to print everything in the MyMinHeap object, regardless of
     * if it is in the scope or not. Also prints value of "next" so we can tell what
     * is in scope.
     */
    public void printEverything() {
        for (int i = 1; i < next; i++) {
            Node n = nodeHeap[i];
            System.out.print(n.val + " ");
        }
        System.out.println("Next: " + next);
    }
}
