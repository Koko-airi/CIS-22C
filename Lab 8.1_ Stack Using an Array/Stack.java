/**
 * Stack class - Array Version
 *
 * @author Michael
 * CIS 22C, Lab 8.1
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Stack<T> implements LIFO<T> {
    private T[] stack;
    private int currSize;
    private final int SIZE = 10;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Queue class
     * with an initial length of 10 and
     * no elements
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        stack = (T[]) new Object[SIZE];
        currSize = 0;

    }

    /**
     * Converts an array into a Stack in the same order
     * @param array the array to copy
     */
    @SuppressWarnings("unchecked")
    public Stack(T[] array) {
        if (array == null)
            return;

        stack = (T[]) new Object[((array.length - 1) % SIZE + 1) * SIZE];

        for (int i = array.length - 1; i >= 0; i--) {
            push(array[i]);
        }

        toString();
    }


    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     */
    @SuppressWarnings("unchecked")
    public Stack(Stack<T> original) {
        if (original == null)
            return;
        stack = (T[]) new Object[original.stack.length];

        for (int i = 0; i < original.stack.length; i++) {

            stack[i] = original.stack[i];
        }
        currSize = original.currSize;
    }

    /****ACCESSORS****/

    //Add methods here

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
//        System.out.println(toString() + "      " + currSize);
        if (isEmpty())
            throw new NoSuchElementException();
        return stack[currSize - 1];
    }

    /**
     * Returns the size of the Stack
     * @return the size from 0 to n
     */
    @Override
    public int getSize() {
        return currSize;
    }


    /**
     * Determines whether a Stack is empty
     * @return whether the Stack contains
     * no elements
     */
    @Override
    public boolean isEmpty() {
        return currSize == 0;
    }

    /****MUTATORS****/

    /**
     * Inserts a new value in the Stack
     *
     * @param data the new data to insert
     * @postcondition a new node in the Stack
     */
    @Override
    public void push(T data) {
        if (currSize == stack.length)
            resize();
//        System.out.println(Arrays.deepToString(stack) + "   " + currSize);
        stack[currSize++] = data;

    }

    /**
     * Removes the front element in the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition the front element has
     * been removed
     */
    @Override
    public void pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        stack[currSize--] = null;
    }

    /****ADDITONAL OPERATIONS****/


    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = currSize - 1; i >= 0; i--) {
            sb.append(stack[i] + " ");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Determines whether two obects are Stacks and
     * contain the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Stack)) {
            return false;
        }
        Stack<T> object = (Stack<T>) obj;

        if (object.currSize != currSize)
            return false;

        for (int i = 0; i < currSize; i++) {
            if (!object.stack[i].equals(stack[i]))
                return false;
        }


        return true;
    }

    /**
     * Creates a String of the Stack in reverse order by calling the
     * recursive helper method.
     * @return a Stack in reverse order
     */
    public String reverseStack() {

//        System.out.println(reverseStack(0) + "\n");
        return reverseStack(0) + "\n";
    }

    /**PRIVATE HELPER METHODS*/

    /**
     * Recursively creates a String where the data is in reverse order.
     * @param index the current index
     * @return a String of this Stack in reverse order
     */
    private String reverseStack(int index) {
        if (index >= currSize)
            return "";
        return stack[index] + " " + reverseStack(++index);
    }

    /**
     * Increases the current array
     * size by 10
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] temp = (T[]) new Object[stack.length + SIZE];
        for (int i = 0; i < stack.length; i++) {

            temp[i] = stack[i];
        }
//        System.out.println(Arrays.deepToString(temp));
//        System.out.println(Arrays.deepToString(stack));

        stack = temp;

//        System.out.println(Arrays.deepToString(temp));
//        System.out.println(Arrays.deepToString(stack));
    }


}
