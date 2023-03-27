public class Main {
    public static void main(String[] args) {

        // Get an input from standard input
        int defaultVal = 2;
        int fileNum = defaultVal;

        if (args.length > 0) {
            fileNum = Integer.parseInt(args[0]);
        }

        DistributeRuns dr = new DistributeRuns(fileNum);

    }
}
