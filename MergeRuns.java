import java.io.*;

class MergeRuns {

    public static File[] fileArray; // to keep track of what file to write to
    public static PrintWriter pw; // to write to files
    public static BufferedReader br; // to read from standard input

    public static void main(String[] args) {
        int fileNum = 2; // default value
        if (args.length > 0) {
            fileNum = Integer.parseInt(args[0]);
        }

        DistributeRuns dr = new DistributeRuns(fileNum);
        fileArray = dr.getFileList();

        merge();
    }

    public static void merge() {
        // do something
    }

}