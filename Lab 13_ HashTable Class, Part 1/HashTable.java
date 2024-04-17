/**
 * HashTable.java
 *
 * @author Michael Chen
 * CIS 22C, Lab 13.2
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashTable<T> {

    private int numElements;
    private ArrayList<LinkedList<T>> table;

    /**
     * Constructor for the HashTable class. Initializes the Table to be sized
     * according to value passed in as a parameter. Inserts size empty Lists into
     * the table. Sets numElements to 0
     *
     * @param size the table size
     * @throws IllegalArgumentException when size <= 0
     * @precondition size > 0
     */
    public HashTable(int size) throws IllegalArgumentException {
        if (size <= 0)
            throw new IllegalArgumentException();
        table = new ArrayList<>();

        for (int i = 0; i < size; i++)
            table.add(new LinkedList<>());
        numElements = 0;
    }

    /**
     * Constructor for HashTable class.
     * Inserts the contents of the given array
     * into the Table at the appropriate indices
     *
     * @param array an array of elements to insert
     * @param size  the size of the Table
     * @throws IllegalArgumentException when size <= 0
     * @precondition size > 0
     */
    public HashTable(T[] array, int size) throws IllegalArgumentException {
        if (array == null)
            return;
        if (size <= 0)
            throw new IllegalArgumentException();
        table = new ArrayList<>();

        for (int i = 0; i < size; i++)
            table.add(new LinkedList<>());
        numElements = 0;

        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    /** Accessors */

    /**
     * Returns the hash value in the table for a given Object.
     *
     * @param obj the Object
     * @return the index in the table
     */
    private int hash(T obj) {
        int code = obj.hashCode();
        return code % table.size();
    }

    /**
     * Counts the number of elements at this index.
     *
     * @param index the index in the table
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException when the precondition is violated
     * @precondition 0 <= index <= table.size()
     */
    public int countBucket(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= table.size())
            throw new IndexOutOfBoundsException();
        return table.get(index).getLength();
    }

    /**
     * Determines total number of elements in the table
     *
     * @return total number of elements
     */
    public int getNumElements() {
        return numElements;
    }

    /**
     * Accesses a specified key in the Table
     *
     * @param elmt the key to search for
     * @return the value to which the specified key is mapped,
     * or null if this table contains no mapping for the key.
     * @throws NullPointerException when the precondition is violated.
     * @precondition <you fill in here>
     */
    public T get(T elmt) throws NullPointerException {
        if (!contains(elmt))
            return null;
        int index = hash(elmt);
        LinkedList<T> temp = table.get(index);
        temp.positionIterator();

        while (!temp.offEnd()) {
            if (temp.getIterator().equals(elmt)) {
                return temp.getIterator();
            }
            temp.advanceIterator();
        }
        return null;
    }

    /**
     * Accesses a specified element in the table.
     *
     * @param elmt the element to locate
     * @return the bucket number where the element
     * is located or -1 if it is not found.
     * @throws NullPointerException when the precondition is violated.
     * @precondition <you fill in here>
     */
    public int find(T elmt) throws NullPointerException {
        if (!contains(elmt))
            return -1;
        int index = hash(elmt);
        LinkedList<T> temp = table.get(index);
        temp.positionIterator();

        while (!temp.offEnd()) {
            if (temp.getIterator().equals(elmt)) {
                return index;
            }
            temp.advanceIterator();
        }
        return -1;
    }

    /**
     * Determines whether a specified element is in the table.
     *
     * @param elmt the element to locate
     * @return whether the element is in the table
     * @throws NullPointerException when the precondition is violated
     * @precondition <you fill in here>
     */
    public boolean contains(T elmt) throws NullPointerException {
        int index = hash(elmt);
        LinkedList<T> temp = table.get(index);
        temp.positionIterator();

        while (!temp.offEnd()) {
            if (temp.getIterator().equals(elmt)) {
                return true;
            }
            temp.advanceIterator();
        }
        return false;
    }

    /** Mutators */

    /**
     * Inserts a new element in the table at the end of the chain
     * of the correct bucket.
     *
     * @param elmt the element to insert
     * @throws NullPointerException when the precondition is violated.
     * @precondition <you fill in here>
     */
    public void add(T elmt) throws NullPointerException {
        int index = hash(elmt);
        LinkedList<T> temp = table.get(index);
        temp.addLast(elmt);
        numElements++;

    }

    /**
     * Removes the given element from the table.
     *
     * @param elmt the element to remove
     * @return whether elmt exists and was removed from the table
     * @throws NullPointerException when the precondition is violated
     * @precondition <you fill in here>
     */
    public boolean delete(T elmt) throws NullPointerException {
        int index = hash(elmt);
        LinkedList<T> temp = table.get(index);
        temp.positionIterator();

        while (!temp.offEnd()) {
            if (temp.getIterator().equals(elmt)) {
                temp.removeIterator();
                return true;
            }
            temp.advanceIterator();
        }
        return false;
    }

    /**
     * Resets the hash table back to the empty state, as if the one argument
     * constructor has just been called.
     */
    public void clear() {
        int size = table.size();
        table = new ArrayList<>();

        for (int i = 0; i < size; i++)
            table.add(new LinkedList<>());
        numElements = 0;
    }

    /** Additional Methods */

    /**
     * Computes the load factor.
     *
     * @return the load factor
     */
    public double getLoadFactor() {
        return (double) numElements / table.size();
    }

    /**
     * Creates a String of all elements at a given bucket
     *
     * @param bucket the index in the table
     * @return a String of elements, separated by spaces with a new line character
     * at the end
     * @throws IndexOutOfBoundsException when bucket is
     *                                   out of bounds
     * @precondition <you fill in here>
     */
    public String bucketToString(int bucket) throws IndexOutOfBoundsException {
        LinkedList<T> temp = table.get(bucket);
        StringBuilder sb = new StringBuilder();
        temp.positionIterator();

        temp.positionIterator();
        while (!temp.offEnd()) {
            sb.append(temp.getIterator().toString() + " ");
            temp.advanceIterator();
        }
        return sb.toString() + "\n";
    }

    /**
     * Creates a String of the bucket number followed by a colon followed by
     * the first element at each bucket followed by a new line. For empty buckets,
     * add the bucket number followed by a colon followed by empty.
     *
     * @return a String of all first elements at each bucket.
     */
    public String rowToString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < table.size(); i++) {
            LinkedList<T> temp = table.get(i);
            sb.append("Bucket " + i + ": ");
            try {
                sb.append(temp.getFirst().toString());
            } catch (NoSuchElementException ex) {
                sb.append("empty");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Starting at the 0th bucket, and continuing in order until the last
     * bucket, concatenates all elements at all buckets into one String, with
     * a new line between buckets and one more new line at the end of the
     * entire String.
     *
     * @return a String of all elements in this HashTable.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < table.size(); i++) {
            LinkedList<T> temp = table.get(i);
            temp.positionIterator();

            temp.positionIterator();
            boolean ran = false;
            while (!temp.offEnd()) {
                sb.append(temp.getIterator().toString() + " ");
                temp.advanceIterator();
                ran = true;
            }
            if (ran)
                sb.append("\n");
        }
        return sb.toString() + "\n";
    }
}
