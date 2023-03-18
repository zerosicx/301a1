import java.io.*;

public class CreateRuns {

    // Keep track of the minheap
    public static MyMinHeap mh;
    // Need to read and write from standard output
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String latestOutput; // Keep track of the last thing we output to standard output
    public static String endOfRunFlag = "-----END-OF-RUN-----";

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
        mh.printHeap(); // Checking the contents of the heap
        createRuns();

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

    public static void createRuns() {
        boolean endOfFile = false;

        while (!endOfFile) { // Always expect an output value
            // While something can be read

            // If mh.next is == 1 (we have shortened the scope as much as possible)
            // Restore the scope
            if (mh.getNext() == 1) {
                System.out.println(endOfRunFlag);
                mh.restoreScope();
                latestOutput = null;
            }

            // If the top of heap is less than latestOutput, shorten scope
            if (latestOutput != null && mh.peek().compareTo(latestOutput) < -1) {
                mh.shortenScope();
            }

            // If the top of heap is NOT less than latestOutput, output and read another
            // line; print and replace
            // Read a new line
            // If the new line is null, set endOfFile = true
            else {
                System.out.println("Add to run: " + mh.peek());
                latestOutput = mh.peek();
                String str = "";

                try {
                    str = reader.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (str == null) {
                    endOfFile = true;
                    break;
                } else {
                    mh.replace(str);
                }
            }

        }

        // Print the rest of the stack
        while (mh.getNext() > 1) {
            System.out.println("Add to run: " + mh.peek());
            mh.remove();
        }
    }
}