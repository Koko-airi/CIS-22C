/**
 * Stack class - singly-linked list version
 *
 * @author Michael
 * CIS 22C, Lab 6
 */

import java.util.NoSuchElementException;

public class Stack<T> implements LIFO<T> {
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
     * @param array an array of elements to copy
     * e.g. [1,2,3] becomes 1-> 2->3->null
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
     * @return whether the Stack contains
     * no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the Stack
     * @return the size from 0 to n
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Returns the value stored at the front
     * of the Stack
     * @return the value at the front of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
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
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
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
}
