import java.io.*;

public class DistributeRuns {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;

    // list of temporary files
    public static File[] fileList;
    private static int pointer = 0;
    private static PrintWriter[] writerList;

    /**
     * Constructor class
     * Reads standard input from create runs and distributes them
     * according to the number of files givem
     * 
     * @param files Number of files
     */
    public DistributeRuns(int files) {
        // check if valid number of files and set it to 2 if not
        if (files <= 1) {
            System.err.println("Number of files must be greater than 1. Defaulted to 2.");
            files = 2;
        }

        // creates the files and adds them to the file list
        fileList = new File[files];
        writerList = new PrintWriter[files];
        createFiles(files);

        // accept runs
        accept();
    }

    /**
     * Accepts standart input
     */
    public void accept() {
        try {

            String runLine = reader.readLine();
            while (runLine != null) {
                // need to distribute the inputs evenly among the files
                distribute(runLine);
                runLine = reader.readLine();
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Distributes the input run into text files
     * 
     * @param runLine A line from run to be written in a file
     */
    public void distribute(String runLine) {

        // check if reached the end of the file list and reset pointer
        if (pointer == fileList.length) {
            pointer = 0;
        }

        try {
            // print to the correct file and point to the next writer at the end of the run
            PrintWriter fileWriter = writerList[pointer];
            fileWriter.println(runLine);

            if (runLine.equals(CreateRuns.endOfRunFlag)) {
                pointer++;
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Creates temporary text files and lists all the printwriters
     * 
     * @param files Number of files to create
     */
    public void createFiles(int files) {
        for (int i = 0; i < files; i++) {
            try {
                File temp = File.createTempFile("temp" + (i + 1), ".txt");
                // File temp = new File("temp" + (i + 1) + ".txt");
                fileList[i] = temp;
                writerList[i] = new PrintWriter(new BufferedWriter(new FileWriter(fileList[i])), true);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    /**
     * Returns the list of files where the runs are distributed into
     */
    public File[] getFileList() {
        return fileList;
    }
}