/**
 * LabProgram.java
 * @author Your name
 * @author Partner's name
 * CIS 22C, Lab 19
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/*
 * This comparator will compare Integer objects using compare().
 */
class IntComparator implements Comparator<Integer> {
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
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}

/*
 * This comparator will compare String objects using compare().
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
        return o1.compareTo(o2);
    }
}

public class LabProgram {
    private StrComparator strCmp = new StrComparator();
    private IntComparator intCmp = new IntComparator();

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testInsert()");
        if (lab.testInsert() == 0) {
            msg("-No errors found");
        }

        msg("Calling testRemove()");
        if (lab.testRemove() == 0) {
            msg("-No errors found");
        }

        msg("Calling testSort()");
        if (lab.testSort() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testInsert() {
        int cntErr = 0, max = 0;
        String str, maxStr;
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(1));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        hi.insert(2);
        //assertEquals(2, hi.getMax());
        max = hi.getMax();
        if (max != 2) {
            msg("-With heap: 1 then insert(2), getMax() must return 2 not "
                + max);
            cntErr++;
        }
        hi.insert(3);
        //assertEquals(3, hi.getMax());
        max = hi.getMax();
        if (max != 3) {
            msg("-With heap: 2, 1 then insert(3), getMax() must return 3 not "
                + max);
            cntErr++;
        }
        //assertEquals("3, 1, 2", hi.toString());
        str = hi.toString();
        if (!str.equals("3, 1, 2")) {
            msg("-With heap: 1 and inserting: 2, 3, toString() must return "
                + "3, 1, 2 not " + str);
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("a", "b", "C"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        hs.insert("p");
        //assertEquals("p", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("p")) {
            msg("-With heap b, a, C then insert(\"p\"), getMax() must return "
                + "\"p\" not \"" + maxStr + "\".");
            cntErr++;
        }
        hs.insert("r");
        //assertEquals("r", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("r")) {
            msg("-With heap p, b, C, a then insert(\"r\"), getMax() must "
                + "return \"r\" not \"" + maxStr + "\".");
            cntErr++;
        }
        hs.insert("i");
        //assertEquals("r", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("r")) {
            msg("-With heap r, p, C, a, b then insert(\"i\"), getMax() must "
                + "return \"r\" not \"" + maxStr + "\".");
            cntErr++;
        }
        hs.insert("o");
        //assertEquals("r", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("r")) {
            msg("-With heap r, p, C, a, b then insert(\"o\"), getMax() must "
                + "return \"r\" not \"" + maxStr + "\".");
            cntErr++;
        }
        str = hs.toString();
        //assertEquals("r, p, o, a, b, C, i", hs.toString());
        if (!str.equals("r, p, o, a, b, C, i")) {
            msg("-Starting with: a, b, C and inserting: p, r, i, o, toString()"
                + " must\n return r, p, o, a, b, C, i\n"
                + String.format("%" + 4 + "s", " ") + "not " + str);
            cntErr++;
        }
        return cntErr;
    }

    private int testRemove() {
        int cntErr = 0;
        String str = "";
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(1, 2, 3));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        hi.remove(1);
        //assertEquals("2, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("2, 1")) {
            msg("-With heap: 3, 2, 1 then calling remove(1), toString()"
                + " must return 2, 1 not " + str);
            cntErr++;
        }
        hi.remove(2);
        //assertEquals("2, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("2")) {
            msg("-With heap: 2, 1 then calling remove(2), toString()"
                + " must return 2 not " + str);
            cntErr++;
        }
        hi.remove(1);
        //assertEquals("", hi.toString());
        str = hi.toString();
        if (!str.equals("")) {
            msg("-With heap: 2 then calling remove(1), toString()"
                + " must return \"\" (empty string) not \"" + str + "\"");
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("a", "b", "C"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        hs.remove(3);
        //assertEquals("b, a", hs.toString());
        str = hs.toString();
        if (!str.equals("b, a")) {
            msg("-With heap: b, a, C then calling remove(3), toString()"
                + " must return b, a not " + str);
            cntErr++;
        }
        hs.remove(1);
        //assertEquals("b, a", hs.toString());
        str = hs.toString();
        if (!str.equals("a")) {
            msg("-With heap: b, a then calling remove(1), toString()"
                + " must return a not " + str);
            cntErr++;
        }
        hs.remove(1);
        //assertEquals("", hs.toString());
        str = hs.toString();
        if (!str.equals("")) {
            msg("-With heap: a then calling remove(1), toString()"
                + " must return \"\" (empty string) not \"" + str + "\"");
            cntErr++;
        }
        return cntErr;
    }

    private int testSort() {
        int cntErr = 0;
        String str = "";
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(4, 7, 8, 3, 2, 6, 5));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        ArrayList<Integer> sorted = hi.sort();
        //assertEquals("[2, 3, 4, 5, 6, 7, 8]", sorted.toString());
        if (!sorted.toString().equals("[2, 3, 4, 5, 6, 7, 8]")) {
            msg("-Starting with the heap: 8, 7, 6, 3, 2, 4, 5 then "
                + "sort()\n  must return ArrayList [2, 3, 4, 5, 6, 7, 8]\n"
                + String.format("%" + 20 + "s", " ") + "not " + sorted);
            cntErr++;
        }
        str = hi.toString();
        //assertEquals("8, 7, 6, 3, 2, 4, 5", hi.toString());
        str = hi.toString();
        if (!str.equals("8, 7, 6, 3, 2, 4, 5")) {
            msg("-Starting with the heap: 8, 7, 6, 3, 2, 4, 5 then after"
                + " sort()\n  toString() must return 8, 7, 6, 3, 2, 4, 5\n"
                + String.format("%" + 21 + "s", " ") + "not " + str);
            cntErr++;
        }
        iLst = new ArrayList<>(
            java.util.Arrays.asList(4, 7, 7, 7, 5, 0, 2, 3, 5, 1));
        hi = new Heap<>(iLst, intCmp);
        sorted = hi.sort();
        //assertEquals("[0, 1, 2, 3, 4, 5, 5, 7, 7, 7]", sorted.toString());
        if (!sorted.toString().equals("[0, 1, 2, 3, 4, 5, 5, 7, 7, 7]")) {
            msg("-Starting with the heap: 7, 7, 7, 5, 5, 0, 2, 3, 4, 1 then "
                + "sort()\n  must return ArrayList [0, 1, 2, 3, 4, 5, 5, 7, 7, 7]\n"
                + String.format("%" + 20 + "s", " ") + "not " + sorted);
            cntErr++;
        }
        str = hi.toString();
        //assertEquals("7, 7, 7, 5, 5, 0, 2, 3, 4, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("7, 7, 7, 5, 5, 0, 2, 3, 4, 1")) {
            msg("-Starting with the heap: 7, 7, 7, 5, 5, 0, 2, 3, 4, 1 then after"
                + " sort()\n  toString() must return 7, 7, 7, 5, 5, 0, 2, 3, 4, 1\n"
                + String.format("%" + 21 + "s", " ") + "not " + str);
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("P", "r", "i", "o", "r", "i", "t", "y"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        ArrayList<String> sortStr = hs.sort();
        if (!sortStr.toString().equals("[P, i, i, o, r, r, t, y]")) {
            msg("-Starting with the heap: y, r, t, o, r, i, i, P then "
                + "sort()\n  must return ArrayList [P, i, i, o, r, r, t, y]\n"
                + String.format("%" + 20 + "s", " ") + "not " + sorted);
            cntErr++;
        }
        //assertEquals("y, r, t, o, r, i, i, P", hs.toString());
        str = hs.toString();
        if (!str.equals("y, r, t, o, r, i, i, P")) {
            msg("-Starting with the heap: y, r, t, o, r, i, i, P then after"
                + " sort()\n  toString() must return y, r, t, o, r, i, i, P\n"
                + String.format("%" + 21 + "s", " ") + "not " + str);
            cntErr++;
        }
        return cntErr;
    }
}
