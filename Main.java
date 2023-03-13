public class Main {
    public static void main(String[] args) {
        // Create a MinHeap
        MyMinHeap mh = new MyMinHeap(23);
        mh.insert("hello");
        mh.insert("there");
        mh.insert("swee");
        mh.insert("swee");
        mh.printHeap();
    }
}
