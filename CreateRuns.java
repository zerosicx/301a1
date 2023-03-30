
/**
 * Made by Hannah Carino 1585791
 * and Kyle Ananayo 1558266
 */

import java.io.*;

/**
 * CreateRuns reads from the console and creates a MinHeap of the specified
 * size.
 * 
 * Runs are iteratively created using a MinHeap with replacement selection and
 * ends of runs are indicated by an endOfRunFlat.
 */
public class CreateRuns {

    // Keep track of the minheap
    public static MyMinHeap mh;
    // Need to read and write from standard output
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String latestOutput; // Keep track of the last thing we output to standard output
    public static String endOfRunFlag = "-----END-OF-RUN-----";

    /**
     * Reads from standard input and creates a MinHeap of that size. Populates the
     * heap with data read from standard input.
     * 
     * @param args
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
        createRuns();
    }

    /**
     * Populates a heap of a specific size by iteratively reading from the standard
     * input <size> amount of times. If the size is higher than the amount of data
     * available, it is handled in the MinHeaps' load() method which is called.
     * 
     * @param size
     */
    public static void populateHeap(int size) {
        String[] lineArray = new String[size];
        String str = "";

        // Fill an array with the size of the MinHeap.
        for (int i = 0; i < lineArray.length; i++) {
            try {
                str = reader.readLine();
                lineArray[i] = str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mh.load(lineArray); // Orders the array for what we need
    }

    /**
     * Creates runs while there is still data being read from standard input. If
     * there is no more data to read or the scope is shortened such that the
     * MinHeap's "next" value points to the root, a new run is initialised.
     */
    public static void createRuns() {
        boolean endOfFile = false;

        while (!endOfFile) {
            if (latestOutput == null || latestOutput == endOfRunFlag) {
                endOfFile = printAndReplace();
            } else {
                String top = mh.peek();
                if (top.compareTo(latestOutput) >= 0) {
                    endOfFile = printAndReplace();
                } else {
                    mh.shortenScope(); // Something wrong here - can result in null null null a b c d
                    if (mh.getNext() <= 1) {
                        mh.restoreScope(); // Restores MinHeap by setting next to the end and reheaping
                        System.out.println(endOfRunFlag);
                        latestOutput = endOfRunFlag;
                    }
                }
            }
        }

        // Print the rest of the stack
        while (mh.getNext() > 1) {
            System.out.println(mh.peek());
            mh.remove();
        }
        System.out.println(endOfRunFlag);
    }

    /**
     * Prints and replaces the top value of the MinHeap. If this method is called,
     * we know the top of the MinHeap can be output in the current run. If there is
     * no more data to read, the run is finished and a new run is started with the
     * remaining data still in the heap.
     * 
     * @return boolean
     */
    public static boolean printAndReplace() {
        // Read a value from standard input
        String str = "";
        try {
            str = reader.readLine();
            if (str == null) { // If we are at the end of the input, create a new run and
                // restore heap scope
                if (latestOutput != endOfRunFlag) {
                    System.out.println(endOfRunFlag);
                }
                mh.restoreScope();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(mh.peek());
        latestOutput = mh.peek();
        mh.replace(str);

        return false;
    }
}