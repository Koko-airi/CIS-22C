/**
 * Stack class - singly-linked list version
 *
 * @author Michael
 * CIS 22C, Lab 6
 */

import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements LIFO<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Stack class
     *
     * @postcondition a new Stack object with all fields
     * assigned default values
     */
    public Stack() {
        top = null;
        size = 0;
    }

    /**
     * Constructor for the Stack class
     * Converts an array into a Stack in the same order
     *
     * @param array an array of elements to copy
     *              e.g. [1,2,3] becomes 1-> 2->3->null
     */
    public Stack(T[] array) {

        if (array == null)
            return;
        for (int i = array.length - 1; i >= 0; i--) {
            push(array[i]);
        }
    }

    /**
     * Copy constructor for the Stack class
     *
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     * REQUIRED: THIS METHOD MUST BE IMPLEMENTED
     * IN O(N) TIME
     */
    public Stack(Stack<T> original) {
        if (original == null || original.isEmpty())
            return;
        size = original.size;
        Node ori = original.top;


        Node prev = new Node(ori.data);
        top = prev;
        boolean first = true;
        ori = ori.next;
        for (int i = 0; i < original.size - 1; i++) {
            prev.next = new Node(ori.data);

            ori = ori.next;
            prev = prev.next;
        }

    }

    /****ACCESSORS****/

    /**FILL IN HERE*/

    /**
     * Determines whether a Stack is empty
     *
     * @return whether the Stack contains
     * no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the Stack
     *
     * @return the size from 0 to n
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Returns the value stored at the front
     * of the Stack
     *
     * @return the value at the front of the Stack
     * @throws NoSuchElementException when the
     *                                precondition is violated
     * @precondition !isEmpty()
     */
    @Override
    public T peek() throws NoSuchElementException {
//        System.out.println(toString() + "  " + size);
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        return top.data;
    }
    /****MUTATORS****/

    /**FILL IN HERE*/

    /**
     * Removes the front element in the Stack
     *
     * @throws NoSuchElementException when
     *                                the precondition is violated
     * @precondition !isEmpty()
     * @postcondition the front element has
     * been removed
     */
    public void pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");

        top = top.next;
        size--;

    }

    /**
     * Inserts a new value in the Stack
     *
     * @param data the new data to insert
     * @postcondition a new node in the Stack
     */
    public void push(T data) {
        if (size == 0) {
            top = new Node(data);
            size = 1;
            return;
        }
        Node temp = top;
        top = new Node(data);
        top.next = temp;
        size++;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     *
     * @return a String of Stack values
     */
    public String toString() {
        String ans = "";
        Node temp = top;
        while (temp != null) {
//            ans = temp.data + " " + ans;
            ans += temp.data + " ";
            temp = temp.next;
        }
        return ans + "\n";
    }

    /**
     * Determines whether two Stacks contain
     * the same values in the same order
     *
     * @param obj the Object to compare to this Stack
     * @return whether obj and this Stack are equal
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Stack))
            return false;
        Stack<T> object = (Stack<T>) obj;
        if (size != object.size)
            return false;

//        System.out.println(this);
//        System.out.println(object);
        Node temp = top;
        Node temp1 = object.top;
        while (temp != null) {

//            System.out.println(temp.data+"  "+temp1.data );
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
        return isSorted(top);
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
        return linearSearch(top, element);

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
        if (getMid(top, mid).compareTo(value) == 0) {
//            System.out.println("True");
            return true;
        }
        if (getMid(top, mid).compareTo(value) < 0)
            return binarySearch(mid + 1, high, value);

        return binarySearch(low, mid - 1, value);
    }

    /**
     * Creates a String of the Stack in reverse order by calling the
     * recursive helper method.
     *
     * @return the Stack values as a String in reverse order.
     */
    public String reverseStack() {
        return reverseStack(top) + "\n";
    }

/** RECURSIVE HELPER METHOD */

    /**
     * Recursively (no loops) creates a String
     * where the data is in reverse order
     *
     * @param node the current node.
     * @return the Stack values as a String in reverse order.
     */
    private String reverseStack(Node node) {
        if (node == null)
            return "";
        return reverseStack(node.next) + node.data.toString() + " ";
    }

}
