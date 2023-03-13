import java.util.ArrayList;

class MyMinHeap {

    private final int MIN_VAL = 4;
    private ArrayList<String> heapArray;
    private int next;

    public MyMinHeap(int size) {
        if (size < MIN_VAL) {
            size = MIN_VAL;
        }
        heapArray = new ArrayList<String>(size);
        heapArray.add(0, "");
        this.next = 1;
    }

    /** Inserts data to the next position in the heap */
    public void insert(String s) {
        heapArray.add(next, s);
        this.next++;

        // Don't need to upheap as we can assume the CreateRuns will only ever insert
        // the right item
    }

    public void remove() {
        // Swap with bottom element
        String temp = heapArray.get(next - 1); // Get the bottom element
        heapArray.set(1, temp); // Set it as the root
        heapArray.set(next - 1, ""); // The value of the bottom element does not matter as it's going to be replaced
        this.next--; // Decrement next

        // TODO: add downheap to put root in the right place
    }

    
    // replaces the first value in the heap and reheaps
    public String replace(String s) {
        // check if the first value of the heap array is 
        if(heapArray.get(1)!=null){
            // temp store first value in heap array
            String out = heapArray.get(1);
            // set the first value of the array to the given string
            heapArray.set(1, s);
            // rearrange the heap if necessary
            reheap();
            // return the smallest value of the array
            return out;
        }
        // if heap array is empty, add the string to the top of array
        heapArray.add(1, s);
        return null;
    }


    // returs the first value in the heap
    public String peek() {
        // check if heap array is empty
        if(heapArray.size() < 3){ return null;}
        // return the first value in hte heap
        return heapArray.get(1);
    }

    // load the given array as heap
    public void load(ArrayList<String> stringArray) {
        // add contents of the string array to the heap array
        for(String s: stringArray){
            heapArray.add(s);
        }
    }

    // rearranges the heap so the first value has the least value
    public void reheap() {

        int indexParent = 1;
        String parent = heapArray.get(indexParent);


        // check if parent is greater any of its children
        while(parent.compareTo(heapArray.get(2*indexParent))>0 || 
            parent.compareTo(heapArray.get(2*indexParent+1))>0){

            int indexChildL = indexParent*2;
            int indexChildR = (indexParent*2)+1;

            // check if parent is greater than left child and swap
            if(parent.compareTo(heapArray.get(2*indexParent))>0){
                swap(indexParent,indexChildL);
                parent = heapArray.get(indexChildL);
                indexParent = heapArray.indexOf(parent);
            }

            // check if parent is greater than right child and swap
            if(parent.compareTo(heapArray.get(2*indexParent+1))>0){
                swap(indexParent,indexChildR);
                parent = heapArray.get(indexChildR);
                indexParent = heapArray.indexOf(parent);
            }

        }

        return;
    }


    // swaps the values in the heap array with the given indexes
    public void swap(int pos1, int pos2){
        String string1 = heapArray.get(pos1);
        heapArray.set(pos1, heapArray.get(pos2));
        heapArray.set(pos2, string1);
    }

    public void printHeap() {
        for (int i = 0; i < this.next; i++) {
            System.out.println(heapArray.get(i));
        }
    }
}