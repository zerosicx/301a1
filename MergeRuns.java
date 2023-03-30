
/**
 * Made by Hannah Carino 1585791
 * and Kyle Ananayo 1558266
 */

import java.io.*;
import java.util.Arrays;

class MergeRuns {

    public static File[] fileArray; // to keep track of what file to write to
    public static PrintWriter pw;
    public static BufferedReader[] brArray; // to read from every file

    public static void main(String[] args) {
        int fileNum = 2; // default value
        if (args.length > 0) {
            fileNum = Integer.parseInt(args[0]);
        }

        DistributeRuns dr = new DistributeRuns(fileNum);
        fileArray = dr.getFileList();

        File result = merge();
        printToFile(result); // Debugging
        printToOutput(result);
        // System.out.println("Merged!"); // Debugging
    }

    /**
     * While there is more than one non-empty output file, impement MergeRuns
     * algorithm.
     * 
     * @return
     */
    public static File merge() {

        File[] outputFileArr = createOutputFiles();
        initialiseBufferedReaders();

        // Initialising flag arrays to keep track of the which files have ended
        // and which have reached end of run
        Boolean[] eofFlagArray = new Boolean[fileArray.length];
        Boolean[] eorFlagArray = new Boolean[fileArray.length];
        Arrays.fill(eofFlagArray, false);
        Arrays.fill(eorFlagArray, false);

        // Initilising Node array and NodeMinHeap to be filled
        Node[] nodeArray = new Node[fileArray.length];
        NodeMinHeap nmh = new NodeMinHeap(fileArray.length);

        int currFile = 0; // Which file to read from
        try {
            while (fileArray.length > 1) {
                if (oneRemaining()) // if only one file in fileArray
                    break;

                // Resetting the flag arrays
                Arrays.fill(eofFlagArray, false);
                Arrays.fill(eorFlagArray, false);

                // While not ALL of the files have ended
                while (!allTrue(eofFlagArray)) {
                    // The file to output current run to
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFileArr[currFile], true)));

                    for (int i = 0; i < nodeArray.length; i++) {
                        String str = brArray[i].readLine();
                        nodeArray[i] = new Node(str, i);

                        if (nodeArray[i].val == null) {
                            eofFlagArray[i] = true;
                            eorFlagArray[i] = true;
                        }
                    }

                    nmh.load(nodeArray); // Loads and sorts it automatically

                    // While all of the runs have not ended, the NodeMinHeap is not empty, and
                    // the NodeMinHeap's root value is not null, implement print and replace
                    while (!allTrue(eorFlagArray) && nmh.peek() != null && nmh.peek().val != null) {
                        pw.println(nmh.peek().val);
                        pw.flush(); // empty the PrintWriter

                        int fileRef = nmh.peek().ref;
                        String str = brArray[fileRef].readLine();

                        if (str != null) {
                            // STOPPING CONDITION: WHEN A RUN HAS ENDED, REMOVE FROM MH
                            // as not reading from the file anymore (for the remainder
                            // of the current run)
                            if (str.equals(CreateRuns.endOfRunFlag)) {
                                // Remove from the NodeMinHeap as we are not reading from it currently
                                eorFlagArray[fileRef] = true;
                                nmh.remove();
                            } else {
                                Node node = new Node(str, fileRef);
                                nmh.replace(node);
                            }

                        } else {
                            // STOPPING CONDITION: IF THIS IS ALL SET TO TRUE, ALL FILES ENDED
                            eofFlagArray[fileRef] = true; // indicate that the file has ended
                        }
                    }

                    // STOPPING CONDITION: WHEN ALL CURRENT RUNS HAVE ENDED BUT FILES NOT ENDED
                    if (!allTrue(eofFlagArray) && allTrue(eorFlagArray)) {
                        // Print that it is the end of the run
                        pw.println(CreateRuns.endOfRunFlag);
                        pw.flush();

                        Arrays.fill(nodeArray, null); // Reset node array to refill

                        // increment or wrap the current file we are outputting to
                        if (currFile == fileArray.length - 1) {
                            currFile = 0;
                        } else {
                            currFile++;
                        }
                    }

                    Arrays.fill(eorFlagArray, false);
                }

                // Tidying up: when we have reached the end of a file and there
                // is still more than one file remaining, close and recreate utilities

                // Reset file number
                currFile = 0;

                // Close buffered readers
                closeBufferedReaders();

                // Empty the files in fileArray
                for (int i = 0; i < fileArray.length; i++) {
                    PrintWriter delete = new PrintWriter(fileArray[i]);
                    delete.close();
                }

                // The outputFileArr => fileArray, and outputFileArr should become empty.
                File[] temp = fileArray;
                fileArray = outputFileArr;
                outputFileArr = temp;

                // Create new buffered readers
                initialiseBufferedReaders();

            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return fileArray[0];
    }

    /**
     * A helper function to create the output files
     * 
     * @return
     */
    private static File[] createOutputFiles() {
        File[] outputFileArr = new File[fileArray.length];

        try {
            for (int i = 0; i < outputFileArr.length; i++) {
                outputFileArr[i] = File.createTempFile("tempFile" + (i + 1), ".txt");
                outputFileArr[i].deleteOnExit();
            }
        } catch (Exception e) {
            System.err.println("Had trouble creating temporary files: " + e);
        }

        return outputFileArr;

    }

    /**
     * Helper function to initialise buffered readers depending on the number of
     * files in fileArray. For cleaner code.
     */
    private static void initialiseBufferedReaders() {
        brArray = new BufferedReader[fileArray.length];

        try {
            for (int i = 0; i < brArray.length; i++) {
                brArray[i] = new BufferedReader(new FileReader(fileArray[i]));
            }
        } catch (Exception e) {
            System.err.println("Had trouble initialising array of buffered readers: " + e);
        }
    }

    /**
     * A helper function to check if all booleans in a Boolean array are true.
     * 
     * @param bools
     * @return
     */
    private static boolean allTrue(Boolean[] bools) {
        for (Boolean b : bools) {
            if (b == false) {
                return false;
            }
        }

        return true;
    }

    /**
     * A helper function to close buffered readers. Good practice.
     */
    private static void closeBufferedReaders() {
        try {
            for (int i = 0; i < brArray.length; i++) {
                brArray[i].close();
            }
        } catch (Exception e) {
            System.err.println("Had trouble closing buffered readers: " + e);
        }
    }

    /**
     * A helper function to check if all files are empty except for the first file
     * 
     * @return
     */
    private static Boolean oneRemaining() {
        try {
            BufferedReader br;
            int count = 0;

            // Other than the first file, are any other files not empty?
            for (int i = 1; i < fileArray.length; i++) {
                br = new BufferedReader(new FileReader(fileArray[i]));
                String s = br.readLine();
                if (s != null)
                    count++;
            }

            if (count == 0) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("Had trouble checking if more than one output file is non-empty: " + e);
        }
        return false;
    }

    /**
     * Helper function to turn the final single temporary file into a permanent
     * file.
     * 
     * @param f
     */
    private static void printToFile(File f) {

        File finalOutput = new File("finalOutput.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(finalOutput)));

            String str = reader.readLine();
            while (str != null && !str.equals(CreateRuns.endOfRunFlag)) {
                // System.out.println(str);
                pw.println(str);
                pw.flush();
                str = reader.readLine();
            }

            reader.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function to turn the final single temporary file into a permanent
     * file.
     * 
     * @param f
     */
    private static void printToOutput(File f) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));

            String str = reader.readLine();
            while (str != null && !str.equals(CreateRuns.endOfRunFlag)) {
                System.out.println(str);
                str = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}