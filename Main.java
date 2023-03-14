public class Main {
    public static void main(String[] args) {

        MyMinHeap mh = new MyMinHeap(25);
        mh.insert("c");
        mh.insert("b");
        mh.insert("a");
        mh.insert("d");
        mh.printHeap();

        mh.remove();
        mh.printHeap();

        mh.replace("e");
        mh.printHeap();

    }
}
