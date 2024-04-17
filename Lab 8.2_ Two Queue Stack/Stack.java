/**
 * Stack class - Two Queue Version
 *
 * @author Michael
 * CIS 22C, Lab 8.2
 */

import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements LIFO<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    /**** CONSTRUCTORS ****/

    /**
     * Default constructor for the Stack class
     */
    public Stack() {
        queue1 = new Queue<>();
        queue2 = new Queue<>();
    }

    /**
     * Converts an array into a Stack in the same order.
     * @param array the array to copy
     */
    public Stack(T[] array) {
        queue1 = new Queue<>();
        queue2 = new Queue<>();
        if (array == null) {
            return;
        }

        for (int i = 0; i < array.length; i++)
            queue1.enqueue(array[i]);
    }

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is an identical,
     * but distinct, copy of original
     */
    public Stack(Stack<T> original) {
        queue1 = new Queue<>();
        queue2 = new Queue<>();
        if (original == null) {
            return;
        }

        for (int i = 0; i < original.getSize(); i++) {
            queue1.enqueue(original.queue1.getFront());
            original.queue1.enqueue(original.queue1.getFront());
            original.queue1.dequeue();
        }
    }

    /**** ACCESSORS ****/

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
        return queue1.getFront();
    }

    /**
     * Returns the size of the Stack
     *
     * @return the size from 0 to n
     */
    @Override
    public int getSize() {
        return queue1.getSize();
    }

    /**
     * Determines whether a Stack is empty
     *
     * @return whether the Stack contains
     * no elements
     */
    @Override
    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    /**** MUTATORS ****/

    /**
     * Inserts a new value in the Stack
     *
     * @param data the new data to insert
     * @postcondition a new node in the Stack
     */
    @Override
    public void push(T data) {
        queue1.enqueue(data);
        for (int i = queue1.getSize(); i > 1; i--) {
            queue2.enqueue(queue1.getFront());
            queue1.dequeue();
        }

        while (!queue2.isEmpty()) {
            queue1.enqueue(queue2.getFront());
            queue2.dequeue();
        }
    }

    /**
     * Removes the front element in the Stack
     *
     * @throws NoSuchElementException when
     *                                the precondition is violated
     * @precondition !isEmpty()
     * @postcondition the front element has
     * been removed
     */
    @Override
    public void pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        queue1.dequeue();
    }

    /**** ADDITONAL OPERATIONS ****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "\n";
//        StringBuilder sb = new StringBuilder();
//
//        while(!queue1.isEmpty())
//        {
//            sb.append(queue1.getFront().toString() + " ");
//            queue1.enqueue(queue1.getFront());
//            queue1.dequeue();
//        }
//
//        while(!queue2.isEmpty())
//        {
//            queue1.enqueue(queue2.getFront());
//            queue2.dequeue();
//        }
        return queue1.toString();
    }

    /**
     * Determines whether two objects are Stacks and
     * contain the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Stack))
            return false;

        Stack<T> object = (Stack<T>) obj;
        if (object.getSize() != getSize())
            return false;


        return queue1.equals(object.queue1);
    }


    /**
     * Creates a String of the Stack in reverse order.
     * @return a Stack in reverse order
     */
    public String reverseStack() {


        return queue1.reverseQueue();
    }
}
