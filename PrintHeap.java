import java.util.Arrays;

/** 
* https://stackoverflow.com/questions/36385868/java-how-to-print-heap-stored-as-array-level-by-level
*/

public class PrintHeap {

    public void printHeap(int[] arr, int next) {

        System.out.println();

        int height = log2(next) + 1;

        for (int i = 1, len = next; i < len; i++) {
            int x = arr[i];
            int level = log2(i) + 1;
            int spaces = (height - level + 1) * 2;

            System.out.print(stringOfSize(spaces, ' '));
            System.out.print(x);

            if((int)Math.pow(2, level) - 1 == i) System.out.println();
        }
        System.out.println("\n");
    }

    private String stringOfSize(int size, char ch) {
        char[] a = new char[size];
        Arrays.fill(a, ch);
        return new String(a);
    }

    // log with base 2
    private int log2(int x) {
        return (int)(Math.log(x) / Math.log(2)); // = log(x) with base 10 / log(2) with base 10
    }
}