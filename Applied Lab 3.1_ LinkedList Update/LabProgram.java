/**
 * LabProgram.java
 * CIS 22C, Applied Lab 3.1
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testNumberedListString()");
        if (lab.testNumberedListString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testFindIndex()");
        if (lab.testFindIndex() == 0) {
            msg("-No errors found");
        }
        msg("Calling testAdvanceIteratorToIndex()");
        if (lab.testAdvanceIteratorToIndex() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testNumberedListString() {
        int cntErr = 0;
        String numList = "";
        LinkedList<Integer> emptyList = new LinkedList<>();
        numList = emptyList.numberedListString();
        if (!numList.equals("\n")) {
            msg("-numberedListString() must return: (empty String)\\n\nNOT:\n"
                + numList + "for empty LinkedList.");
            cntErr++;
        }
        LinkedList<String> sList = new LinkedList<>(new String[] {"A", "B", "C"});
        numList = sList.numberedListString();
        /*assertEquals("1. A\n" +
                "2. B\n" +
                "3. C\n\n",
                sList.numberedListString());
        */
        if (!numList.equals("1. A\n" +
                "2. B\n" +
                "3. C\n\n")) {
            msg("-numberedListString() must return:\n1. A\\n\n" +
                "2. B\\n\n" +
                "3. C\\n\\n\nNOT:\n" + numList);
            cntErr++;
        }
        LinkedList<Integer> intList = new LinkedList<>(new Integer[] {1, 2, 3});
        numList = intList.numberedListString();
        if (!numList.equals("1. 1\n" +
                "2. 2\n" +
                "3. 3\n\n")) {
            msg("-numberedListString() must return:\n1. 1\\n\n" +
                "2. 2\\n\n" +
                "3. 3\\n\\n\nNOT:\n" + numList);
            cntErr++;
        }
        return cntErr;
    }

    private int testFindIndex() {
        int cntErr = 0, idx = 0;
        LinkedList<Integer> iList = new LinkedList<>();
        //assertEquals(-1, iList.findIndex(4));
        idx =  iList.findIndex(4);
        if (idx != -1) {
            msg("-findIndex() must return -1 when called on empty list.");
            cntErr++;
        }
        LinkedList<String> sList = new LinkedList<>(new String[] {"!", "?", ".", ","});
        //assertEquals(0, sList.findIndex("!"));
        idx =  sList.findIndex("!");
        if (idx != 0) {
            msg("-findIndex(\"!\") must return 0 not " + idx + " for list {"
                + sList.toString().trim() + "}");
            cntErr++;
        }
        //assertEquals(1, sList.findIndex("?"));
        idx =  sList.findIndex("?");
        if (idx != 1) {
            msg("-findIndex(\"?\") must return 1 not " + idx + " for list {"
                + sList.toString().trim() + "}");
            cntErr++;
        }
        //assertThrows(NullPointerException.class, ()->{sList.getIterator();});
        try {
            sList.getIterator();
            msg("-getIterator() must throw NullPointerException despite"
                + " previously calling findIndex().\n--findIndex() must not"
                + " move or otherwise make use of iterator.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        //assertEquals(2, sList.findIndex("."));
        idx =  sList.findIndex(".");
        if (idx != 2) {
            msg("-findIndex(\".\") must return 2 not " + idx + " for list {"
                + sList.toString().trim() + "}");
            cntErr++;
        }
        //assertEquals(3, sList.findIndex(","));
        idx =  sList.findIndex(",");
        if (idx != 3) {
            msg("-findIndex(\",\") must return 3 not " + idx + " for list {"
                + sList.toString().trim() + "}");
            cntErr++;
        }
        return cntErr;
    }

    private int testAdvanceIteratorToIndex() {
        int cntErr = 0;
        String str = "";
        LinkedList<Integer> iList = new LinkedList<>();
        //assertThrows(NullPointerException.class, ()->{iList.advanceIteratorToIndex(3);});
        try {
            iList.advanceIteratorToIndex(3);
            msg("-advanceIteratorToIndex(3) must throw IndexOutOfBoundsException"
                + " for empty list {" + iList.toString().trim() + "}");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        LinkedList<String> sList = new LinkedList<>(new String[] {"!", "?", ".", ","});
        //assertThrows(NullPointerException.class, ()->{sList.advanceIteratorToIndex(2);});
        try {
            sList.advanceIteratorToIndex(-1);
            msg("-advanceIteratorToIndex(3) must throw IndexOutOfBoundsException"
                + " when called on a negative index");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        sList.positionIterator();
        sList.advanceIteratorToIndex(2);
        //assertEquals(".", sList.getIterator());
        str =  sList.getIterator();
        if (!str.equals(".")) {
            msg("-getIterator() must return \".\" not \"" + str + "\" after "
                + " calling advanceIteratorToIndex(2) for list {"
                + sList.toString().trim() + "}");
            cntErr++;
        }
        //assertThrows(NullPointerException.class, ()->{sList.advanceIteratorToIndex(4);});
        try {
            sList.advanceIteratorToIndex(4);
            msg("-advanceIteratorToIndex(4) must throw IndexOutOfBoundsException"
                + " for going beyond end of list {" + sList.toString().trim()
                + "}");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        sList.positionIterator();
        sList.advanceIteratorToIndex(3);
        //assertEquals(",", sList.getIterator());
        str =  sList.getIterator();
        if (!str.equals(",")) {
            msg("-getIterator() must return \",\" not \"" + str + "\" after "
                + " calling advanceIteratorToIndex(3) for list {"
                + sList.toString().trim() + "}");
            cntErr++;
        }
        sList.positionIterator();
        sList.advanceIteratorToIndex(0);
        //assertEquals("!", sList.getIterator());
        str =  sList.getIterator();
        if (!str.equals("!")) {
            msg("-getIterator() must return \"!\" not \"" + str + "\" after "
                + " calling advanceIteratorToIndex(0) for list {"
                + sList.toString().trim() + "}");
            cntErr++;
        }
        return cntErr;
    }
}
