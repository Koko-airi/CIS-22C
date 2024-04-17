/**
 * LabProgram.java
 * CIS 22C, Lab 14
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private final int SIZE = 10;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testHashTableTArrayInt()");
        if (lab.testHashTableTArrayInt() == 0) {
            msg("-No errors found");
        }
        msg("Calling testCountBucket()");
        if (lab.testCountBucket() == 0) {
            msg("-No errors found");
        }
        msg("Calling testContains()");
        if (lab.testContains() == 0) {
            msg("-No errors found");
        }
        msg("Calling testDelete()");
        if (lab.testDelete() == 0) {
            msg("-No errors found");
        }
        msg("Calling testClear()");
        if (lab.testClear() == 0) {
            msg("-No errors found");
        }
        msg("Calling testRowToString()");
        if (lab.testRowToString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testToString()");
        if (lab.testToString() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testHashTableTArrayInt() {
        int cntErr = 0, numElems = 0;
        double load = 0;
        Student[] array = null;
        HashTable<Student> ht = new HashTable<>(array, SIZE);
        //assertEquals(0, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 0) {
            msg("-getNumElements() must return 0 not " + numElems
                    + " when constructor called with null array.");
            cntErr++;
        }
        array = new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66)};
        ht = new HashTable<>(array, 10);
        //assertEquals(3, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != array.length) {
            msg("-getNumElements() must return " + array.length
                    + " not " + numElems + " when constructor called with "
                    + array.length + " element array.");
            cntErr++;
        }
        //assertEquals(0.3, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.3) >= 0.001) {
            msg("-getLoadFactor() must return 0.3 not " + load
                    + " for HashTable size 10 with " + + array.length + " elements.");
            cntErr++;
        }
        //assertEquals(4, ht.find(new Student("Gus", 44)));
        Student stu = new Student("Gus", 44);
        int bucket = ht.find(stu);
        if (bucket != 4) {
            msg("-find() must return bucket 4 not " + bucket + " for student("
                    + stu + ").");
            cntErr++;
        }
        //assertEquals("Gus: 44 \n", ht.bucketToString(4));
        String str =  ht.bucketToString(4);
        if (!str.equals("Gus: 44 \n")) {
            msg("-HashTable.bucketToString(4) must return \"Gus: 44 \\n\"");
            cntErr++;
        }
        String[] array2 = {"a", "b", "c"};
        //assertThrows(IllegalArgumentException.class, ()-> {new HashTable<String>(array2, -1);});
        try {
            HashTable<String> sht = new HashTable<String>(array2, -1);
            msg("-Must throw IllegalArgumentException when size is <= 0.");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testCountBucket() {
        int cntErr = 0, cnt = 0;
        HashTable<Student> ht = new HashTable<>(new Student[] {
                new Student("Gus", 44), new Student("Tanya", 55),
                new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        //assertEquals(0, ht.countBucket(0));
        cnt = ht.countBucket(0);
        if (cnt != 0) {
            msg("-countBucket(0) must return 0 not " + cnt
                    + " for bucket: {" + ht.bucketToString(0).trim() + "}");
            cntErr++;
        }
        //assertEquals(1, ht.countBucket(4));
        cnt = ht.countBucket(4);
        if (cnt != 1) {
            msg("-countBucket(4) must return 1 not " + cnt
                    + " for bucket: {" + ht.bucketToString(4).trim() + "}");
            cntErr++;
        }
        //assertEquals(2, ht.countBucket(5));
        cnt = ht.countBucket(5);
        if (cnt != 2) {
            msg("-countBucket(5) must return 2 not " + cnt
                    + " for bucket: {" + ht.bucketToString(5).trim() + "}");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.countBucket(-1);});
        try {
            cnt =  ht.countBucket(-1);
            msg("-countBucket(-1) must throw IndexOutOfBoundsException when"
                    + " index is outside bounds of the table.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.countBucket(10);});
        try {
            cnt =  ht.countBucket(10);
            msg("-countBucket(10) must throw IndexOutOfBoundsException when"
                    + " index is outside bounds of the table.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testContains() {
        int cntErr = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertFalse(empty.contains(new Student("Gus", 44)));
        if (empty.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return false for "
                    + "an empty HashTable.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        //assertTrue(ht.contains(new Student("Gus", 44)));
        if (!ht.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return true when"
                    + " bucket 4 contains: " + ht.bucketToString(4).trim());
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Maria", 77)));
        if (ht.contains(new Student("Maria", 77))) {
            msg("-contains(new Student(\"Maria\", 77)) must return false "
                    + "when HashTable does NOT include Student(\"Maria\", 77).");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.contains(noStu);});
        try {
            ht.contains(noStu);
            msg("-contains(element) must throw NullPointerException when"
                    + " element to find is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testDelete() {
        int cntErr = 0, cnt = 0;
        String prev = "";
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        //assertTrue(ht.delete(new Student("Gus", 44)));
        prev = ht.bucketToString(4).trim(); // before deletion
        if (!ht.delete(new Student("Gus", 44))) {
            msg("-delete(new Student(\"Gus\", 44)) must return true when"
                    + " bucket 4 contains: " + prev);
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Gus", 44)));
        if (ht.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return false when"
                    + " element was previously deleted.");
            cntErr++;
        }
        //assertEquals(0, ht.countBucket(4));
        cnt = ht.countBucket(4);
        if (cnt != 0) {
            msg("-countBucket(4) must return 0 not " + cnt
                    + " for previously deleted bucket contents.");
            cntErr++;
        }
        //assertTrue(ht.delete(new Student("Tay", 45)));
        prev = ht.bucketToString(5).trim(); // before deletion
        if (!ht.delete(new Student("Tay", 45))) {
            msg("-delete(new Student(\"Tay\", 45)) must return true when"
                    + " bucket 5 contains: " + prev);
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Tay", 45)));
        if (ht.contains(new Student("Tay", 45))) {
            msg("-contains(new Student(\"Tay\", 45)) must return false when"
                    + " element was previously deleted.");
            cntErr++;
        }
        //assertEquals(1, ht.countBucket(5));
        cnt = ht.countBucket(5);
        if (cnt != 1) {
            msg("-countBucket(5) must return 1 not " + cnt
                    + " when bucket 5 is: " + ht.bucketToString(5).trim());
            cntErr++;
        }
        //assertFalse(ht.delete(new Student("Tay", 45)));
        if (ht.delete(new Student("Tay", 45))) {
            msg("-delete(new Student(\"Tay\", 45)) must return false when"
                    + " element was previously deleted.");
            cntErr++;
        }
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertFalse(empty.contains(new Student("Tay", 45)));
        if (empty.contains(new Student("Tay", 45))) {
            msg("-contains(new Student(\"Tay\", 45)) must return false when"
                    + " HashTable is empty.");
            cntErr++;
        }

        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.delete(noStu);});
        try {
            ht.delete(noStu);
            msg("-contains(element) must throw NullPointerException when"
                    + " element to delete is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    //@Test
    private int testClear() {
        int cntErr = 0, cnt = 0, numElems = 0;
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        ht.clear();
        for(int i = 0; i < SIZE; i++) {
            //assertEquals(0, ht.countBucket(i));
            cnt = ht.countBucket(i);
            if (cnt != 0) {
                msg("-countBucket(" + i + ") must return 0 not " + cnt
                        + " after calling clear().");
                cntErr++;
            }
        }
        //assertEquals(0, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 0) {
            msg("-getNumElements() must return 0 not " + numElems
                    + " after calling clear().");
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Gus", 44)));
        if (ht.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return false"
                    + " after calling clear().");
            cntErr++;
        }
        return cntErr;
    }

    private int testRowToString() {
        int cntErr = 0;
        String str = "";
        HashTable<Student> empty = new HashTable<>(SIZE);
        /*assertEquals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: empty\n" +
                "Bucket 5: empty\n" +
                "Bucket 6: empty\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n",
                empty.rowToString());
        */
        str =  empty.rowToString();
        if (!str.equals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: empty\n" +
                "Bucket 5: empty\n" +
                "Bucket 6: empty\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n")) {

            msg("-rowToString() must return:\nBucket 0: empty\n" +
                    "Bucket 1: empty\n" +
                    "Bucket 2: empty\n" +
                    "Bucket 3: empty\n" +
                    "Bucket 4: empty\n" +
                    "Bucket 5: empty\n" +
                    "Bucket 6: empty\n" +
                    "Bucket 7: empty\n" +
                    "Bucket 8: empty\n" +
                    "Bucket 9: empty\nNOT:\n" + str);
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        /*assertEquals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: Gus: 44\n" +
                "Bucket 5: Tanya: 55\n" +
                "Bucket 6: Andrea: 66\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n",
                ht.rowToString());
         */
        str =  ht.rowToString();
        if (!str.equals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: Gus: 44\n" +
                "Bucket 5: Tanya: 55\n" +
                "Bucket 6: Andrea: 66\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n")) {

            msg("-rowToString() must return:\nBucket 0: empty\n" +
                    "Bucket 1: empty\n" +
                    "Bucket 2: empty\n" +
                    "Bucket 3: empty\n" +
                    "Bucket 4: Gus: 44\n" +
                    "Bucket 5: Tanya: 55\n" +
                    "Bucket 6: Andrea: 66\n" +
                    "Bucket 7: empty\n" +
                    "Bucket 8: empty\n" +
                    "Bucket 9: empty\nNOT:\n" + str);
            cntErr++;
        }
        return cntErr;
    }

    private int testToString() {
        int cntErr = 0;
        String str = "";
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals("\n", empty.toString());
        str =  empty.toString();
        if (!str.equals("\n")) {
            msg("-toString() must return \\n for an empty HashTable.");
        }
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        /*assertEquals("Gus: 44 \n" +
                "Tanya: 55 Tay: 45 \n" +
                "Andrea: 66 \n\n", ht.toString());
        */
        str =  ht.toString();
        if (!str.equals("Gus: 44 \n" +
                "Tanya: 55 Tay: 45 \n" +
                "Andrea: 66 \n\n")) {
            msg("-toString() must return\nGus: 44 \n" +
                    "Tanya: 55 Tay: 45 \n" +
                    "Andrea: 66 \\n\\n\nNOT\n" + str);
        }
        return cntErr;
    }
}




/**
 * LabProgram.java
 * CIS 22C, Lab 13
 */
/*
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private final int SIZE = 10;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testHashTableInt()");
        if (lab.testHashTableInt() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetNumElements()");
        if (lab.testGetNumElements() == 0) {
            msg("-No errors found");
        }
        msg("Calling testAdd()");
        if (lab.testAdd() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetLoadFactor()");
        if (lab.testGetLoadFactor() == 0) {
            msg("-No errors found");
        }
        msg("Calling testBucketToString()");
        if (lab.testBucketToString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testFind()");
        if (lab.testFind() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGet()");
        if (lab.testGet() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testHashTableInt() {
        int cntErr = 0;
        int numElems = 0;
        double load = 0.0;
        HashTable<Student> ht = new HashTable<>(SIZE);
        //assertEquals(0, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 0) {
            msg("-HashTable.getNumElements() must return 0 not " + numElems
                    + " when HashTable has no elements.");
            cntErr++;
        }
        ht.add(new Student("Gus", 44));
        //assertEquals(1, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 1) {
            msg("-HashTable.getNumElements() must return 1 not " + numElems
                    + " for HashTable with 1 element.");
            cntErr++;
        }
        //assertEquals(.1, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.1) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.1 not " + load
                    + " for HashTable size 10 with 1 element.");
            cntErr++;
        }
        //assertThrows(IllegalArgumentException.class, ()-> {new HashTable<String>(-1);});
        try {
            ht = new HashTable<>(-1);
            msg("-Must throw IllegalArgumentException when size is <= 0.");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetNumElements() {
        int cntErr = 0;
        int numElems = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(0, empty.getNumElements());
        numElems = empty.getNumElements();
        if (numElems != 0) {
            msg("-HashTable.getNumElements() must return 0 not " + numElems
                    + " for HashTable with no elements.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44), new Student("Tanya", 55),
                new Student("Andrea", 66), new Student("Tay", 45)};
        for (int idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals(4, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != students.length) {
            msg("-HashTable.getNumElements() must return " + students.length
                    + " not " + numElems + " for HashTable with " + students.length
                    + " elements.");
            cntErr++;
        }
        return cntErr;
    }

    private int testAdd() {
        int cntErr = 0;
        int numElems = 0;
        int bucket = 0;
        String str = "";
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student gus = new Student("Gus", 44);
        bucket = gus.hashCode() % SIZE;
        ht.add(gus);
        //assertEquals(1, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 1) {
            msg("-HashTable.getNumElements() must return 1 not " + numElems
                    + " for HashTable with 1 element.");
            cntErr++;
        }
        //assertTrue(ht.contains(new Student("Gus", 44))); // N/A use following
        //assertEquals("Gus: 44 \n", ht.bucketToString(bucket));
        str =  ht.bucketToString(bucket);
        if (!str.equals("Gus: 44 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                    + ") must return \"" + gus + " \\n\""
                    + " after adding Student(" + gus + ")");
            cntErr++;
        }
        Student tay = new Student("Tay", 45);
        bucket = tay.hashCode() % SIZE;
        ht.add(tay);
        //assertEquals(2, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 2) {
            msg("-HashTable.getNumElements() must return 2 not " + numElems
                    + " for HashTable with 2 elements.");
            cntErr++;
        }
        //assertEquals("Tay: 45 \n", ht.bucketToString(bucket));
        str =  ht.bucketToString(bucket);
        if (!str.equals("Tay: 45 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                    + ") must return \"" + tay + " \\n\""
                    + " after adding Student(" + tay + ")");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.add(noStu);});
        try {
            ht.add(noStu);
            msg("-Must throw NullPointerException when the object to add"
                    + " is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetLoadFactor() {
        int cntErr = 0;
        double load = 0.0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(0.0, empty.getLoadFactor());
        load = empty.getLoadFactor();
        if (Math.abs(load - 0.0) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.0 not " + load
                    + " for HashTable size 10 with no elements.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66),
                new Student("Tay", 45)};
        for (int idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals(0.4, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.4) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.4 not " + load
                    + " for HashTable size 10 with 4 elements.");
            cntErr++;
        }
        //ht.delete(new Student("Gus", 44));
        //assertEquals(0.3, ht.getLoadFactor());
        // no delete yet so use following
        ht.add(new Student("Joey", 21));
        //assertEquals(0.5, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.5) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.5 not " + load
                    + " for HashTable size 10 with 5 elements.");
            cntErr++;
        }
        return cntErr;
    }

    private int testBucketToString() {
        int cntErr = 0;
        int bucket = 0;
        String str = "";
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals("\n", empty.bucketToString(0));
        str =  empty.bucketToString(0);
        if (!str.equals("\n")) {
            msg("-HashTable.bucketToString(0) must return \"\\n\""
                    + " for empty HashTable size " + SIZE + ".");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66),
                new Student("Tay", 45)};
        for (int idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals("Tanya: 55 Tay: 45 \n", ht.bucketToString(5));
        bucket = students[1].hashCode() % SIZE;
        str =  ht.bucketToString(bucket);
        if (!str.equals("Tanya: 55 Tay: 45 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                    + ") must return \"Tanya: 55 Tay: 45 \\n\""
                    + " for HashTable size " + SIZE + ".");
            cntErr++;
        }
        //assertEquals("Gus: 44 \n", ht.bucketToString(4));
        bucket = students[0].hashCode() % SIZE;
        str =  ht.bucketToString(bucket);
        if (!str.equals("Gus: 44 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                    + ") must return \"Gus: 44 \\n\""
                    + " for HashTable size " + SIZE + ".");
            cntErr++;
        }
        //assertEquals("\n", ht.bucketToString(0));
        bucket = 0;
        str =  ht.bucketToString(bucket);
        if (!str.equals("\n")) {
            msg("-HashTable.bucketToString(" + bucket
                    + ") must return \"\\n\""
                    + " for HashTable size " + SIZE + ".");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.bucketToString(-1);});
        try {
            str =  ht.bucketToString(-1);
            msg("-HashTable.bucketToString(-1) must throw "
                    + "IndexOutOfBoundsException.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.bucketToString(10);});
        try {
            str =  ht.bucketToString(10);
            msg("-HashTable.bucketToString(10) must throw "
                    + "IndexOutOfBoundsException for HashTable size " + SIZE + ".");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testFind() {
        int cntErr = 0, idx = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(-1, empty.find(new Student("Gus", 44)));
        idx = empty.find(new Student("Gus", 44));
        if (idx != -1) {
            msg("-find(new Student(\"Gus\", 44)) must return -1 not " + idx
                    + " for an empty HashTable.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44), new Student("Tanya", 55),
                new Student("Andrea", 66), new Student("Tay", 45)};
        for (idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals(4, ht.find(new Student("Gus", 44)));
        idx = ht.find(new Student("Gus", 44));
        if (idx != 4) {
            msg("-find(new Student(\"Gus\", 44)) must return 4 not " + idx
                    + " when bucket 4 contains: "
                    + ht.bucketToString(4).trim());
            cntErr++;
        }
        //assertEquals(-1, ht.find(new Student("Maria", 77)));
        idx = ht.find(new Student("Maria", 77));
        if (idx != -1) {
            msg("-find(new Student(\"Maria\", 77)) must return -1 not " + idx
                    + " when HashTable does NOT include Student(\"Maria\", 77).");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.find(noStu);});
        try {
            ht.find(noStu);
            msg("-find(element) must throw NullPointerException when"
                    + " element to find is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGet() {
        int cntErr = 0, idx = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(null, empty.find(new Student("Gus", 44)));
        Student stu = empty.get(new Student("Gus", 44));
        if (stu != null) {
            msg("-get(new Student(\"Gus\", 44)) must return null not " + stu
                    + " for an empty HashTable.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44), new Student("Tanya", 55),
                new Student("Andrea", 66), new Student("Tay", 45)};
        for (idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals("Gus: 44", ht.get(new Student("Gus", 44)));
        stu = ht.get(new Student("Gus", 44));
        if (!stu.toString().equals("Gus: 44")) {
            msg("-get(new Student(\"Gus\", 44)).toString() must return "
                    + "\"Gus: 44\" not " + stu + " when bucket 4 contains: "
                    + ht.bucketToString(4).trim());
            cntErr++;
        }
        //assertEquals(null, ht.find(new Student("Maria", 77)));
        stu = ht.get(new Student("Maria", 77));
        if (stu != null) {
            msg("-get(new Student(\"Maria\", 77)) must return null not " + stu
                    + " when HashTable does NOT include Student(\"Maria\", 77).");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.get(noStu);});
        try {
            ht.get(noStu);
            msg("-get(element) must throw NullPointerException when"
                    + " element to get is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }
}

 */
