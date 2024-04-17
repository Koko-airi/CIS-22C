/**
 * LabProgram.java
 * CIS 22C, Lab 10
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

        msg("Calling testBSTArray()");
        if (lab.testBSTArray() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetHeight()");
        if (lab.testGetHeight() == 0) {
            msg("-No errors found");
        }
        msg("Calling testSearch()");
        if (lab.testSearch() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetSize()");
        if (lab.testGetSize() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testBSTArray() {
        int cntErr = 0;
        //assertThrows(IllegalArgumentException.class, ()->{BST<String> bst1 = new BST<>(new String[] {"C", "B", "A"});});
        try {
            BST<String> bst1 = new BST<>(new String[] {"C", "B", "A"}, strCmp);
            bst1.getRoot();
            msg("-Must throw IllegalArgumentException on unsorted array argument.");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        String[] array = null;
        BST<String> bst2 = new BST<>(array, strCmp);
        //assertTrue(bst2.isEmpty());
        if (!bst2.isEmpty()) {
            msg("-isEmpty() must return true on null array argument.");
            cntErr++;
        }
        array = new String[] {"A", "B", "C", "D", "E"};
        BST<String> bst3 = new BST<>(array, strCmp);
        //assertEquals("C", bst3.getRoot());
        String strMid =  bst3.getRoot();
        if (!strMid.equals("C")) {
            msg("-BST.getRoot() must return \"C\" not \"" + strMid
                    + "\" for array " + Arrays.toString(array));
            cntErr++;
        }
        //assertEquals(bst3.levelOrderString(), "C A D B E");
        String los = bst3.levelOrderString();
        if (!los.equals("C A D B E \n")) {
            msg("-BST.levelOrderString() must return [C A D B E]\n      for array "
                    + Arrays.toString(array) + " not [" + los.trim() +"]");
            cntErr++;
        }
        //assertEquals(2, bst3.getHeight()); // put in testGetHeight()
        array = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        BST<String> bst4 = new BST<>(array, strCmp);
        strMid =  bst4.getRoot();
        //assertEquals("5", bst4.getRoot());
        if (!strMid.equals("5")) {
            msg("-BST.getRoot() must return \"5\" not \"" + strMid
                    + "\" for array " + Arrays.toString(array));
            cntErr++;
        }
        //assertEquals(bst4.levelOrderString(), "5 2 7 1 3 6 8 4 9 \n");
        los = bst4.levelOrderString();
        if (!los.equals("5 2 7 1 3 6 8 4 9 \n")) {
            msg("-BST.levelOrderString() method must return  [5 2 7 1 3 6 8 4 9]"
                    + "\n from array " + Arrays.toString(array)
                    + " not [" + los.trim() +"]");
            cntErr++;
        }
        return cntErr;
    }

    private int testGetHeight() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        //assertEquals(-1, bst.getHeight());
        int height = bst.getHeight();
        if (height != -1) {
            msg("-BST.getHeight() must return -1 not " + height
                    + " for empty BST");
            cntErr++;
        }
        bst.insert("C", strCmp);
        //assertEquals(0, bst.getHeight());
        height = bst.getHeight();
        if (height != 0) {
            msg("-BST.getHeight() must return 0 not " + height
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        bst.insert("A", strCmp);
        bst.insert("D", strCmp);
        //assertEquals(1, bst.getHeight());
        height = bst.getHeight();
        if (height != 1) {
            msg("-BST.getHeight() must return 1 not " + height
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        bst.insert("B", strCmp);
        //assertEquals(2, bst.getHeight());
        height = bst.getHeight();
        if (height != 2) {
            msg("-BST.getHeight() must return 2 not " + height
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        return cntErr;
    }

    private int testSearch() {
        int cntErr = 0;
        BST<String> bst1 = new BST<>();
        //assertFalse(bst1.search("A"));
        if (bst1.search("A", strCmp) != null) {
            msg("-search() must return null on empty BST.");
            cntErr++;
        }
        BST<String> bst4 = new BST<>(
                new String[] {"10", "2", "3", "4", "5", "6", "7", "8", "9"}, strCmp);
        //assertTrue(bst4.search("10"));
        if (!bst4.search("10", strCmp).equals("10")) {
            msg("-search(\"10\") must return value \"10\" for BST {"
                    + bst4.levelOrderString().trim() + "}");
            cntErr++;
        }
        //assertFalse(bst4.search("1"));
        if (bst4.search("1", strCmp) != null) {
            msg("-search(\"1\") must return null for BST {"
                    + bst4.levelOrderString().trim() + "}");
            cntErr++;
        }
        return cntErr;
    }

    private int testGetSize() {
        int cntErr = 0;
        int size = 0;
        BST<String> bst = new BST<>();
        //assertEquals(0, bst.getSize());
        size = bst.getSize();
        if (size != 0) {
            msg("-BST.getSize() must return 0 not " + size
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        bst.insert("C", strCmp);
        //assertEquals(1, bst.getSize());
        size = bst.getSize();
        if (size != 1) {
            msg("-BST.getSize() must return 1 not " + size
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        bst.insert("A", strCmp);
        bst.insert("B", strCmp);
        bst.insert("D", strCmp);
        //assertEquals(4, bst.getSize());
        size = bst.getSize();
        if (size != 4) {
            msg("-BST.getSize() must return 4 not " + size
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        return cntErr;
    }
}
