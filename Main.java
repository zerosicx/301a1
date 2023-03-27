public class Main {
    public static void main(String[] args) {

        // Get an input from standard input
        int defaultVal = 2;
        int fileNum = defaultVal;

        if (args.length > 0) {
            fileNum = Integer.parseInt(args[0]);
        }

        DistributeRuns dr = new DistributeRuns(fileNum);

        /* Testing functionality of NodeMinHeap */
        // Node n2 = new Node("World", 2);
        // Node n1 = new Node("Hello", 1);

        // Node[] nodeArray = new Node[] { n1, n2 };

        // NodeMinHeap nmh = new NodeMinHeap(2);
        // nmh.load(nodeArray);
        // nmh.printEverything();

    }
}
