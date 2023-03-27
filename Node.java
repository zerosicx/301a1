
public class Node {

    public String val;
    public int ref;

    /**
     * Keeps track of a value of a line, with reference to the file it originally
     * belonged to.
     * 
     * @param value
     * @param reference
     */
    public Node(String value, int reference) {
        val = value;
        ref = reference;
    }
}
