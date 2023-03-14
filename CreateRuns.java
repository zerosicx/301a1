import java.io.*;

public class CreateRuns {

    // Keep track of the minheap
    public static MyMinHeap mh;
    // Need to read and write from standard output
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static String latestOutput; // Keep track of the last thing we output to standard output

    /**
     * This class reads from the console and creates a MinHeap of the specified
     * size.
     * 
     * It then reads data until minheap's "next" value is larger than the console
     * input + 1.
     * 
     * Note: this can be tested by calling "cat dummyData.text | java CreateRuns 10"
     * It's 10 because I currently only put 10 lines in the dummyData. It should
     * correctly sort.
     * 
     */
    public static void main(String[] args) {

        int size = 25; // default size of minheap is 25

        if (args.length < 1) {
            System.err.println("Usage: CreateRuns <MyMinHeap size>");
        } else {
            size = Integer.parseInt(args[0]);
        }

        mh = new MyMinHeap(size);
        // Fill it with data
        populateHeap(size);
        mh.printHeap();

    }

    public static void populateHeap(int size) {
        String[] lineArray = new String[size];
        String str = "";

        for (int i = 0; i < lineArray.length; i++) {
            // Try read a line {
            try {
                str = reader.readLine();
                lineArray[i] = str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mh.load(lineArray);
    }
}