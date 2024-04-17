/**
 * LabProgram.java
 * CIS 22C, Lab 12
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
 * This comparator will sort String objects using compareTo().
 */
class StrComparator implements Comparator<String> {
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * The implementor must ensure that {@code sgn(compare(x, y)) ==
     * -sgn(compare(y, x))} for all {@code x} and {@code y}.  (This
     * implies that {@code compare(x, y)} must throw an exception if and only
     * if {@code compare(y, x)} throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
     * {@code compare(x, z)>0}.<p>
     * <p>
     * Finally, the implementor must ensure that {@code compare(x, y)==0}
     * implies that {@code sgn(compare(x, z))==sgn(compare(y, z))} for all
     * {@code z}.<p>
     * <p>
     * It is generally the case, but <i>not</i> strictly required that
     * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."<p>
     * <p>
     * In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(String o1, String o2) {

        for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
            if (o1.charAt(i) == o2.charAt(i))
                continue;
            else
                return o1.charAt(i) - o2.charAt(i);
        }
        return o1.length() - o2.length();
    }

}

public class LabProgram {
    private StrComparator strCmp = new StrComparator();

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        int pointsEarned = 0;
        int cntErr = 0;

        msg("Calling testFindMin()");
        if (lab.testFindMin() == 0) {
            msg("-No errors found");
        }
        msg("Calling testFindMax()");
        if (lab.testFindMax() == 0) {
            msg("-No errors found");
        }
        msg("Calling testRemove()");
        if (lab.testRemove() == 0) {
            msg("-No errors found");
        }
        msg("Calling testSharedPrecursor()");
        if (lab.testSharedPrecursor() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testFindMin() {
        int cntErr = 0;
        String str = "";
        BST<String> bst = new BST<>();
        //assertThrows(NoSuchElementException.class, ()->{bst.findMin();});
        try {
            bst.findMin();
            msg("-Must throw NoSuchElementException when the BST is empty.");
            cntErr++;
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        BST<String> bst4 = new BST<>(
                new String[] {"10", "2", "3", "4", "5", "6", "7", "8", "9"}, strCmp);
        //assertEquals("10", bst4.findMin());
        str =  bst4.findMin();
        if (!str.equals("10")) {
            msg("-BST.findMin() must return \"10\" not \"" + str
                    + "\" for BST {" + bst4.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        return cntErr;
    }

    private int testFindMax() {
        int cntErr = 0;
        String str = "";
        BST<String> bst = new BST<>();
        //assertThrows(NoSuchElementException.class, ()->{bst.findMax();});
        try {
            bst.findMax();
            msg("-Must throw NoSuchElementException when the BST is empty.");
            cntErr++;
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        BST<String> bst4 = new BST<>(new String[] {
                "10", "2", "3", "4", "5", "6", "7", "8", "9"}, strCmp);
        //assertEquals("9", bst4.findMax());
        str =  bst4.findMax();
        if (!str.equals("9")) {
            msg("-BST.findMax() must return \"9\" not \"" + str
                    + "\" for BST {" + bst4.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        return cntErr;
    }

    private int testRemove() {
        int cntErr = 0;
        int size = 0;
        String str, orig;
        BST<String> bst = new BST<>(new String[] {
                "1", "2", "3", "4", "5", "6", "7", "8", "9"}, strCmp);
        orig =  bst.levelOrderString().trim();
        bst.remove("5", strCmp);
        //assertEquals("6", bst.getRoot());
        str =  bst.getRoot();
        if (!str.equals("6")) {
            msg("-BST.getRoot() must return \"6\" not \"" + str
                    + "\" for BST {" + orig
                    + "} (level order) after removing \"5\"");
            cntErr++;
        }
        BST<String> states = new BST<>();
        states.insert("HI", strCmp);
        states.insert("MN", strCmp);
        states.insert("CA", strCmp);
        states.insert("IA", strCmp);
        states.insert("MI", strCmp);
        states.insert("AK", strCmp);
        orig =  states.levelOrderString().trim();
        states.remove("HI", strCmp);
        //assertEquals("IA", states.getRoot());
        str =  states.getRoot();
        if (!str.equals("IA")) {
            msg("-BST.getRoot() must return \"IA\" not \"" + str
                    + "\" for BST {" + orig
                    + "} (level order) after removing \"HI\"");
            cntErr++;
        }
        orig =  states.levelOrderString().trim();
        states.remove("MN", strCmp);
        //assertEquals("IA", states.getRoot());
        str =  states.getRoot();
        if (!str.equals("IA")) {
            msg("-BST.getRoot() must return \"IA\" not \"" + str
                    + "\" for BST {" + orig
                    + "} (level order) after removing \"MN\"");
            cntErr++;
        }
        //assertEquals(4, states.getSize());
        size = states.getSize();
        if (size != 4) {
            msg("-BST.getSize() must return 4 not " + size
                    + " for BST {" + states.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        states.remove("CT", strCmp);
        //assertEquals(4, states.getSize());
        size = states.getSize();
        if (size != 4) {
            msg("-BST.getSize() must return 4 not " + size
                    + " for BST {" + states.levelOrderString().trim()
                    + "} (level order) after trying to remove \"CT\"");
            cntErr++;
        }
        orig =  states.levelOrderString().trim();
        states.remove("IA", strCmp);
        //assertEquals("MI", states.getRoot());
        str =  states.getRoot();
        if (!str.equals("MI")) {
            msg("-BST.getRoot() must return \"MI\" not \"" + str
                    + "\" for BST {" + orig
                    + "} (level order) after removing \"IA\"");
            cntErr++;
        }
        states.remove("AK", strCmp);
        //assertEquals(2, states.getSize());
        size = states.getSize();
        if (size != 2) {
            msg("-BST.getSize() must return 2 not " + size
                    + " for BST {" + states.levelOrderString().trim()
                    + "} (level order) after removing \"AK\"");
            cntErr++;
        }
        orig =  states.levelOrderString().trim();
        states.remove("CA", strCmp);
        //assertFalse(states.isEmpty());
        if (states.isEmpty()) {
            msg("-isEmpty() must return false " + " for BST {" + orig
                    + "} (level order) after removing \"CA\"");
            cntErr++;
        }
        orig =  states.levelOrderString().trim();
        states.remove("MI", strCmp);
        //assertTrue(states.isEmpty());
        if (!states.isEmpty()) {
            msg("-isEmpty() must return true " + " for BST {" + orig
                    + "} (level order) after removing \"MI\"");
            cntErr++;
        }
        return cntErr;
    }

    private int testSharedPrecursor() {
        int cntErr = 0;
        String str = "";
        BST<String> states = new BST<>();
        states.insert("MN", strCmp);
        states.insert("HI", strCmp);
        states.insert("CA", strCmp);
        states.insert("IL", strCmp);
        states.insert("MI", strCmp);
        states.insert("AK", strCmp);
        //assertEquals("HI", states.sharedPrecursor("CA", "IL"));
        str =  states.sharedPrecursor("CA", "IL", strCmp);
        if (!str.equals("HI")) {
            msg("-BST.sharedPrecursor(\"CA\", \"IL\") must return \"HI\" not \""
                    + str + "\" for BST {" + states.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        states.insert("AZ", strCmp);
        states.insert("CO", strCmp);
        states.insert("KS", strCmp);
        states.insert("IA", strCmp);
        states.insert("ID", strCmp);
        states.insert("KY", strCmp);
        //assertEquals("HI", states.sharedPrecursor("ID", "AZ"));
        str =  states.sharedPrecursor("ID", "AZ", strCmp);
        if (!str.equals("HI")) {
            msg("-BST.sharedPrecursor(\"CA\", \"IL\") must return \"HI\" not \""
                    + str + "\" for BST {" + states.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        //assertEquals("IL", states.sharedPrecursor("ID", "KY"));
        str =  states.sharedPrecursor("ID", "KY", strCmp);
        if (!str.equals("IL")) {
            msg("-BST.sharedPrecursor(\"ID\", \"KY\") must return \"IL\" not \""
                    + str + "\" for BST {" + states.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        //assertEquals("MN", states.sharedPrecursor("MN", "HI"));
        str =  states.sharedPrecursor("MN", "HI", strCmp);
        if (!str.equals("MN")) {
            msg("-BST.sharedPrecursor(\"MN\", \"HI\") must return \"MN\" not \""
                    + str + "\" for BST {" + states.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        //assertEquals("MN", states.sharedPrecursor("MN", "MN"));
        str =  states.sharedPrecursor("MN", "MN", strCmp);
        if (!str.equals("MN")) {
            msg("-BST.sharedPrecursor(\"MN\", \"MN\") must return \"MN\" not \""
                    + str + "\" for BST {" + states.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        //assertThrows(IllegalArgumentException.class, ()->{states.sharedPrecursor("WA", "KY");});
        try {
            states.sharedPrecursor("WA", "KY", strCmp);
            msg("-BST.sharedPrecursor(\"WA\", \"KY\") must throw "
                    + " IllegalArgumentException for BST {"
                    + states.levelOrderString().trim() + "} (level order)");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        //assertThrows(IllegalArgumentException.class, ()->{states.sharedPrecursor("KY", "WA");});
        try {
            states.sharedPrecursor("KY", "WA", strCmp);
            msg("-BST.sharedPrecursor(\"KY\", \"WA\") must throw "
                    + " IllegalArgumentException for BST {"
                    + states.levelOrderString().trim() + "} (level order)");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        BST<String> empty = new BST<>();
        //assertThrows(IllegalArgumentException.class, ()->{empty.sharedPrecursor("WA", "KY");});
        try {
            empty.sharedPrecursor("WA", "KY", strCmp);
            msg("-BST.sharedPrecursor(\"WA\", \"KY\") must throw "
                    + " IllegalArgumentException for BST {"
                    + empty.levelOrderString().trim() + "} (empty BST)");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        return cntErr;
    }
}
