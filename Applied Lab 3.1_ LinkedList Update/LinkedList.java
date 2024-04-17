/**
 * Defines a doubly-linked list class
 *
 * @author Michael
 */

import java.util.Arrays;
import java.util.DuplicateFormatFlagsException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        /**
         * Node constructor
         *
         * @param data the data to be stored in the node
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /**** CONSTRUCTORS ****/

    /**
     * Instantiates a new LinkedList with default values
     *
     * @postcondition LinkedList is setup and empty.
     */
    public LinkedList() {
        first = last = iterator = null;
        length = 0;
    }

    /**
     * Converts the given array into a LinkedList
     *
     * @param array the array of values to insert into this LinkedList
     * @postcondition hi
     */
    public LinkedList(T[] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            first = last = null;
            return;
        }
        for (T value : array) {
            addLast(value);
        }
    }

    /**
     * Instantiates a new LinkedList by copying another List
     *
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     * but separate, copy of the LinkedList original
     */
//    public LinkedList(LinkedList<T> original) {
//        if (original.isEmpty()) {
//            first = last = null;
//            length = 0;
//            return;
//        }
//        // Copying the list should create new nodes
//        // rather than copying references to the original nodes
//        Node originalNode = original.first;
//        while (originalNode != null) {
//            addLast(originalNode.data);
//            originalNode = originalNode.next;
//        }
//    }
    public LinkedList(LinkedList<T> original) {

        if (original == null || original.first == null) {
            first = last = null;
            length = 0;
            return;
        }

        Node originalNode = original.first;
        while (originalNode != null) {
            addLast(originalNode.data);
            originalNode = originalNode.next;
        }

    }

    /**** ACCESSORS ****/

    /**
     * Returns the value stored in the first node
     *
     * @return the value stored at node first
     * @throws NoSuchElementException if empty
     * @precondition not empty
     */
    public T getFirst() throws NoSuchElementException {
        try {
            return first.data;
        } catch (Exception ex) {
            throw new NoSuchElementException("List is empty.");
        }
    }

    /**
     * Returns the value stored in the last node
     *
     * @return the value stored in the node last
     * @throws NoSuchElementException if empty
     * @precondition not empty
     */
    public T getLast() throws NoSuchElementException {
        try {
            return last.data;
        } catch (Exception ex) {
            throw new NoSuchElementException("List is empty.");
        }
    }

    /**
     * Returns the data stored in the iterator node
     *
     * @return the data stored in the iterator node
     * @precondition not used
     * @throw NullPointerException
     */
    public T getIterator() throws NullPointerException {
        if (offEnd())
            throw new NullPointerException();
        return iterator.data;

    }

    /**
     * Returns the current length of the LinkedList
     *
     * @return the length of the LinkedList from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the LinkedList is currently empty
     *
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns whether the iterator is offEnd, i.e. null
     *
     * @return whether the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }

    /**** MUTATORS ****/

    /**
     * Creates a new first element
     *
     * @param data the data to insert at the front of the LinkedList
     * @postcondition added first
     */
    public void addFirst(T data) {
        Node temp = new Node(data);
        if (length == 0) {
            first = last = temp;
        } else {
            first.prev = temp;
            temp.next = first;
            first = temp;
        }
        length++;
    }

    /**
     * Creates a new last element
     *
     * @param data the data to insert at the end of the LinkedList
     * @postcondition added last
     */
    public void addLast(T data) {
        Node temp = new Node(data);
        if (length == 0) {
            first = last = temp;
        } else {
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        length++;
    }

    /**
     * Inserts a new element after the iterator
     *
     * @param data the data to insert
     * @throws NullPointerException if there is nothing
     * @precondition not used
     */
    public void addIterator(T data) throws NoSuchElementException {
        if (offEnd())
            throw new NoSuchElementException("yes");
        Node temp = new Node(data);

        if (iterator.next == null) {
            iterator.next = temp;
            temp.prev = iterator;
            last = temp;
            length++;
            return;
        }
        iterator.next.prev = temp;
        temp.next = iterator.next;
        iterator.next = temp;
        temp.prev = iterator;
        length++;

    }

    /**
     * removes the element at the front of the LinkedList
     *
     * @throws NoSuchElementException
     * @precondition who cares
     * @postcondition who cares
     */
    public void removeFirst() throws NoSuchElementException {
        if (length == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (length == 1) {
            first = last = iterator = null;
        } else {
            if (iterator == first)
                iterator = null;
            first = first.next;
            first.prev = null;
        }
        length--;
    }

    /**
     * removes the element at the end of the LinkedList
     *
     * @throws NoSuchElementException
     * @precondition who cares
     * @postcondition who cares
     */
    public void removeLast() throws NoSuchElementException {
        if (length == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (length == 1) {
            first = last = iterator = null;
        } else {
            if (iterator == last)
                iterator = null;
            last = last.prev;
            last.next = null;
        }
        length--;
    }

    /**
     * removes the element referenced by the iterator
     *
     * @throws NullPointerException if iterator is null
     * @precondition not used
     * @postcondition not used
     */
    public void removeIterator() throws NoSuchElementException {
        if (offEnd())
            throw new NoSuchElementException("yes");

        if (length == 1) {
            first = last = iterator = null;
            length = 0;
            return;
        }
        length--;
        if (iterator == last) {
            last = iterator.prev;
            last.next = null;
            iterator = null;

            return;
        }
        if (iterator.prev == null) {
            first = iterator.next;
            first.prev = null;
            iterator = null;
            return;

        }
        iterator.prev.next = iterator.next;
        iterator.next.prev = iterator.prev;
        iterator = null;

    }


    /**
     * places the iterator at the first node
     *
     * @postcondition not used
     */
    public void positionIterator() {
        iterator = first;
    }

    /**
     * Moves the iterator one node towards the last
     *
     * @throws NullPointerException if end
     * @precondition not used
     * @postcondition not used
     */
    public void advanceIterator() throws NullPointerException {
        if (offEnd())
            throw new NullPointerException("reached end");
        iterator = iterator.next;
    }

    /**
     * Moves the iterator one node towards the first
     *
     * @throws NullPointerException if Iterator is null
     * @precondition not used
     * @postcondition not used
     */
    public void reverseIterator() throws NullPointerException {
        if (offEnd())
            throw new NullPointerException("reached end");
        iterator = iterator.prev;
    }

    /**** ADDITIONAL OPERATIONS ****/

    /**
     * Re-sets LinkedList to empty as if the
     * default constructor had just been called
     */
    public void clear() {
        first = last = iterator = null;
        length = 0;
    }

    /**
     * Converts the LinkedList to a String, with each value separated by a blank
     * line At the end of the String, place a new line character
     *
     * @return the LinkedList as a String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = first;
        while (temp != null) {
//            System.out.println(temp.data);
            result.append(temp.data + " ");
            if (temp != null)
                temp = temp.next;
        }
        return result.toString() + "\n";
    }

    /**
     * Determines whether the given Object is
     * another LinkedList, containing
     * the same data in the same order
     *
     * @param obj another Object
     * @return whether there is equality
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof LinkedList)) {
            return false;
        }
        LinkedList<T> otherList = (LinkedList<T>) obj;

        if (otherList.length != length) {
            return false;
        }
        Node temp1 = first;
        Node temp2 = otherList.first;

        if (temp1 == temp2)
            return true;
        if (temp2 == null)
            return false;


        while (temp1 != null) {
            if (temp1.data == null && temp2.data == null) {
                temp1 = temp1.next;
                temp2 = temp2.next;
                continue;
            } else if (temp1.data == null || temp2.data == null)
                return false;

            if (!temp1.data.equals(temp2.data)) {
                return false;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
    }


    /**
     * Moves all nodes in the list towards the end
     * of the list the number of times specified
     * Any node that falls off the end of the list as it
     * moves forward will be placed the front of the list
     * For example: [1, 2, 3, 4, 5], numMoves = 2 -> [4, 5, 1, 2 ,3]
     * For example: [1, 2, 3, 4, 5], numMoves = 4 -> [2, 3, 4, 5, 1]
     * For example: [1, 2, 3, 4, 5], numMoves = 7 -> [4, 5, 1, 2 ,3]
     *
     * @param numMoves the number of times to move each node.
     * @throws IllegalArgumentException when numMoves < 0
     * @precondition numMoves >= 0
     * @postcondition iterator position unchanged (i.e. still referencing
     * the same node in the list, regardless of new location of Node)
     */
    public void spinList(int numMoves) throws IllegalArgumentException {

        if (length == 0 || length == 1) {
            return;
        }
        if (numMoves < 0) {
            throw new IllegalArgumentException("Number of moves cannot be negative.");
        }
        numMoves %= length;
        if (numMoves == 0) {
            return;
        }
        Node cur = first;
        for (int i = 0; i < length - numMoves; i++) {
            cur = cur.next;
        }
        first.prev = last;
        last.next = first;

        first = cur;
        last = cur.prev;

        first.prev = null;
        last.next = null;
    }

    /**
     * Splices together two LinkedLists to create a third List
     * which contains alternating values from this list
     * and the given parameter
     * For example: [1,2,3] and [4,5,6] -> [1,4,2,5,3,6]
     * For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6, 3, 4]
     * For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
     *
     * @param list the second LinkedList
     * @return a new LinkedList, which is the result of
     * interlocking this and list
     * @postcondition this and list are unchanged
     */
    public LinkedList<T> altLists(LinkedList<T> list) {

        if (isEmpty()) {
            return new LinkedList<>(list);
        }
        if (list == null || list.isEmpty()) {
            return new LinkedList<>(this);
        }
        LinkedList<T> ans = new LinkedList<>();
        Node p1 = first;
        Node p2 = list.first;


        while (p1 != null || p2 != null) {
            if (p1 != null) {
                ans.addLast(p1.data);
                p1 = p1.next;
            }
            if (p2 != null) {
                ans.addLast(p2.data);
                p2 = p2.next;
            }
        }

        return ans;
    }

    /**
     * Returns each element in the LinkedList along with its
     * numerical position from 1 to n, followed by a newline.
     * @return String the list in string form
     */
    public String numberedListString() {
        StringBuilder sb = new StringBuilder();
        Node temp = first;
        for (int i = 0; i < length; i++) {
            sb.append(i + 1 + ". " + temp.data + "\n");
            temp = temp.next;
        }

        return sb.toString() + "\n";
    }

    /**
     * Searches the LinkedList for a given element's index.
     *
     * @param data the data whose index to locate.
     * @return the index of the data or -1 if the data is not contained
     * in the LinkedList.
     */
    public int findIndex(T data) {
        Node temp = first;

        for (int i = 0; temp != null; i++) {
            if (temp.data.equals(data)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    /**
     * Advances the iterator to location within the LinkedList
     * specified by the given index.
     *
     * @param index the index at which to place the iterator.
     * @throws IndexOutOfBoundsException when index is out of bounds
     * @precondition index >= 0,  index < length
     */
    public void advanceIteratorToIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
        positionIterator();
        for (int i = 0; i < index; i++) {
            advanceIterator();
        }

    }
}
