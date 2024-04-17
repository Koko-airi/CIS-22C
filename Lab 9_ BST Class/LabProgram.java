/**
 * LabProgram.java
 * CIS 22C, Lab 9
 */

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
        msg("Calling testBST()");
        if (lab.testBST() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetRoot()");
        if (lab.testGetRoot() == 0) {
            msg("-No errors found");
        }
        msg("Calling testIsEmpty()");
        if (lab.testIsEmpty() == 0) {
            msg("-No errors found");
        }
        msg("Calling testInsert()");
        if (lab.testInsert() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testBST() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        //assertTrue(bst.isEmpty());
        if (!bst.isEmpty()) {
            msg("-isEmpty() must return true on empty BST.");
            cntErr++;
        }
        //assertEquals(0, bst.getSize());
        if (bst.getSize() != 0) {
            msg("-getSize() must return 0 on empty BST.");
            cntErr++;
        }
        //assertThrows(NoSuchElementException.class, ()->{bst.getRoot();});
        try {
            bst.getRoot();
            msg("-Must throw NoSuchElementException calling getRoot() on empty BST.");
            cntErr++;
        } catch (NoSuchElementException e) {
            // thrown as expected
        }
        return cntErr;
    }

    int testGetRoot() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        //assertThrows(NoSuchElementException.class, ()->{bst.getRoot();});
        try {
            bst.getRoot();
            msg("-Must throw NoSuchElementException calling getRoot() on empty BST.");
            cntErr++;
        } catch (NoSuchElementException e) {
            // thrown as expected
        }
        bst.insert("F", strCmp);
        //assertEquals("F", bst.getRoot());
        if (!bst.getRoot().equals("F")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        bst.insert("A", strCmp);
        bst.insert("C", strCmp);
        bst.insert("P", strCmp);
        bst.insert("Q", strCmp);
        bst.insert("Z", strCmp);
        bst.insert("L", strCmp);
        //assertEquals("F", bst.getRoot());
        if (!bst.getRoot().equals("F")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        //bst.remove("F", strCmp);
        //assertEquals("L", bst.getRoot());
        return cntErr;
    }

    int testIsEmpty() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        //assertTrue(bst.isEmpty());
        if (!bst.isEmpty()) {
            msg("-isEmpty() must return true on empty BST.");
            cntErr++;
        }
        bst.insert("C", strCmp);
        //assertFalse(bst.isEmpty());
        if (bst.isEmpty()) {
            msg("-isEmpty() must return false on non-empty BST.");
            cntErr++;
        }
        bst.insert("A", strCmp);
        bst.insert("B", strCmp);
        bst.insert("D", strCmp);
        //assertFalse(bst.isEmpty());
        if (bst.isEmpty()) {
            msg("-isEmpty() must return false on non-empty BST.");
            cntErr++;
        }
        return cntErr;
    }

    int testInsert() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        bst.insert("A", strCmp);
        bst.insert("B", strCmp);
        bst.insert("C", strCmp);
        bst.insert("D", strCmp);
        bst.insert("E", strCmp);
        //assertEquals("A", bst.getRoot());
        if (!bst.getRoot().equals("A")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        //assertEquals("A B C D E \n", bst.levelOrderString());
        String str = bst.levelOrderString();
        if (!str.equals("A B C D E \n")) {
            msg("-levelOrderString() must return A B C D E \\n not "
                    + str.trim() + " \\n");
            cntErr++;
        }
        // More subtle ordering of strings
        bst = new BST<>();
        bst.insert("ME", strCmp);
        bst.insert("MAY", strCmp);
        bst.insert("MAH", strCmp);
        bst.insert("MO", strCmp);
        bst.insert("MOO", strCmp);
        //assertEquals("ME", bst.getRoot());
        if (!bst.getRoot().equals("ME")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        //assertEquals("ME MAY MO MAH MOO \n", bst.levelOrderString());
        str = bst.levelOrderString();
        if (!str.equals("ME MAY MO MAH MOO \n")) {
            msg("-levelOrderString() must return ME MAY MO MAH MOO \\n not "
                    + str.trim() + " \\n");
            cntErr++;
        }
        return cntErr;
    }
}
