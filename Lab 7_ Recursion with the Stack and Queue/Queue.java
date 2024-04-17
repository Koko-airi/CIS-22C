/**
 * The Queue class definition
 *
 * @author Michael
 * CIS 22C, Lab 5
 * @param <T> the generic data stored in the Queue
 */

import java.util.NoSuchElementException;

public class Queue<T extends Comparable<T>> implements Q<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node front;
    private Node end;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Queue class
     *
     * @postcondition a new Queue object with all fields
     * assigned default values
     */
    public Queue() {
        front = end = null;
        size = 0;
    }

    /**
     * Converts an array into a Queue
     *
     * @param array the array to copy into
     *              the Queue
     */
    public Queue(T[] array) {
        if (array == null)
            return;
        for (T i : array) {
            enqueue(i);
        }
    }

    /**
     * Copy constructor for the Queue class
     * Makes a deep copy of the parameter
     *
     * @param original the Queue to copy
     * @postcondition <You fill in here>
     */
    public Queue(Queue<T> original) {
        if (original == null)
            return;
        if (!original.isEmpty()) {
            front = original.front;
            end = original.end;
            size = original.size;
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored at the front
     * of the Queue
     *
     * @return the value at the front of the queue
     * @throws NoSuchElementException when the
     *                                precondition is violated
     * @precondition !isEmpty()
     */
    public T getFront() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("no front");
        return front.data;
    }

    /**
     * Returns the size of the Queue
     *
     * @return the size from 0 to n
     */
    public int getSize() {
        return size;
    }

    /**
     * Determines whether a Queue is empty
     *
     * @return whether the Queue contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the end of the Queue
     *
     * @param data the new data to insert
     * @postcondition add a value to the end of the queue
     */
    public void enqueue(T data) {

        if (size == 0) {
            front = end = new Node(data);
            size++;
            return;
        }
        size++;
        end.next = new Node(data);
        end = end.next;
    }

    /**
     * Removes the front element in the Queue
     *
     * @throws NoSuchElementException when
     *                                the precondition is violated
     * @precondition queue is not empty
     * @postcondition removes the first element in the queue
     */
    public void dequeue() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("no front");
        if (size == 1) {
            front = end = null;
            size = 0;
            return;
        }

        front = front.next;
        size--;
        return;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Queue
     * as a String, separated by a blank space
     * with a new line character at the end
     *
     * @return a String of Queue values
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = front;
        for (int i = 0; i < size; i++) {
            sb.append(temp.data + " ");
            temp = temp.next;
        }
        return sb.toString() + "\n";
    }

    /**
     * Determines whether two Queues contain
     * the same values in the same order
     *
     * @param obj the Object to compare to this
     * @return whether obj and this are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Queue)) {
            return false;
        }
        Queue<T> object = (Queue<T>) obj;

        if (object.size != size)
            return false;

        Node temp = front;
        Node temp1 = object.front;
        while (temp != null) {
            if (!temp.data.equals(temp1.data))
                return false;
            temp = temp.next;
            temp1 = temp1.next;
        }
        return true;
    }


    /**
     * Determines whether the values are sorted from
     * smallest to largest by calling its recursive helper.
     *
     * @return whether the list is sorted
     */
    public boolean isSorted() {
        if (size < 2)
            return true;
        return isSorted(front);
    }

    /**
     * Uses the recursive linear search algorithm to locate an element.
     *
     * @param element the value to search for
     * @return whether the element is present
     * Note that in the case isEmpty() the element is considered not found
     */
    public boolean linearSearch(T element) {
        if (isEmpty())
            return false;
        return linearSearch(front, element);
    }

    /**
     * Uses the recursive binarySearch algorithm to determine if a specific
     * value is available by calling the private helper method binarySearch.
     *
     * @param value the value to search for
     * @return whether the element is present
     * @throws IllegalStateException when the precondition is violated.
     * @precondition isSorted()
     */
    public boolean binarySearch(T value) throws IllegalStateException {
        if (!isSorted())
            throw new IllegalStateException();
        return binarySearch(0, size - 1, value);
    }

/** RECURSIVE HELPER METHODS */

    /**
     * Helper method to isSorted recursively determines whether
     * data is sorted from smallest to largest.
     *
     * @param node the current node
     * @return whether the data is sorted in ascending order
     */
    private boolean isSorted(Node node) {
        if (node.next == null)
            return true;
        return node.data.compareTo(node.next.data) < 0 && isSorted(node.next);
    }

    /**
     * Searches for the specified value by implementing the recursive
     * linearSearch algorithm.
     *
     * @param value the value to search for
     * @return whether the value exists in the list
     */
    private boolean linearSearch(Node node, T value) {
        if (node == null)
            return false;
        if (node.data.equals(value))
            return true;
        return linearSearch(node.next, value);
    }

    /**
     * Helper method for private binarySearch.
     * Searches for the data stored at a Node in a given "midpoint".
     *
     * @param node the current Node in the list
     * @param mid  the integer location in the list
     * @return the data stored at the mid Node
     */
    private T getMid(Node node, int mid) {
        for (int i = 0; i < mid; i++)
            node = node.next;
        return node.data;
    }

    /**
     * Searches for the specified value by implementing the recursive
     * binarySearch algorithm.
     *
     * @param low   the lowest bounds of the search
     * @param high  the highest bounds of the search
     * @param value the value to search for
     * @return whether the value exists in the list
     */
    private boolean binarySearch(int low, int high, T value) {
        int mid = low + (high - low) / 2;
//        System.out.print(toString());
//        System.out.println("0 1 2 3 4 5 6 7 8 9 0 1 ");
//        System.out.print( low + "  " + mid + "  " + high + "   ");
//        System.out.println("     " + value);
        if (high < low) {
//            System.out.println();
            return false;
        }
        if (getMid(front, mid).compareTo(value) == 0) {
//            System.out.println("True");
            return true;
        }
        if (getMid(front, mid).compareTo(value) < 0)
            return binarySearch(mid + 1, high, value);

        return binarySearch(low, mid - 1, value);
    }

    /**
     * Creates a String of the Queue in reverse order by calling the
     * recursive helper method.
     *
     * @return the Queue values as a String in reverse order.
     */
    public String reverseQueue() {
        return reverseQueue(front) + "\n";
    }

/** RECURSIVE HELPER METHODS */

    /**
     * Recursively (no loops) creates a String where the data is in reverse order
     *
     * @param node the current node.
     * @return the Queue values as a String in reverse order.
     */
    private String reverseQueue(Node node) {
        if (node == null)
            return "";
        return reverseQueue(node.next) + node.data.toString() + " ";
    }

}







