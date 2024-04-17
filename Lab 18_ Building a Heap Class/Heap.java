/**
 * Heap.java
 * @author Michael Chen
 * CIS 22C, Lab 18
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<T> {
    private int heapSize;
    private ArrayList<T> heap;
    private Comparator<T> cmp;

    /**Constructors/

    /**
     * Constructor for the Heap class.
     * Sets heapSize to data size, stores parameters, inserts null at heap
     * element 0, and calls buildHeap().
     * @param data an unordered ArrayList, where element 0 is not used.
     * @param cmp that determines organization of heap
     * based on priority.
     */
    public Heap(ArrayList<T> data, Comparator<T> cmp) {
        heapSize = data.size();
        heap = new ArrayList<>(data);
        heap.add(0, null);

        this.cmp = cmp;
        buildHeap();
    }

    /**Mutators*/

    /**
     * Converts an ArrayList into a valid max heap. Called by constructor.
     * Calls helper method heapify.
     */
    public void buildHeap() {
        for (int i = heapSize / 2; i > 0; i--) {
            heapify(i);
        } 
    }

    /**
     * Helper method to buildHeap, remove, and sort.
     * Bubbles an element down to its proper location within the heap.
     * @param index an index in the heap
     */
    private void heapify(int index) {
        if (index < 1 || index > heapSize) {
            throw new IndexOutOfBoundsException();
        }

        int maxIndex = index;
        int left = 2 * index;
        int right = 2 * index + 1;


        if (left <= heapSize && cmp.compare(heap.get(left), heap.get(maxIndex)) > 0)
            maxIndex = left;

        if (right <= heapSize && cmp.compare(heap.get(right), heap.get(maxIndex)) > 0)
            maxIndex = right;

//        System.out.println(maxIndex +"     " +  toString() + "     " + index + "   " + left + "   " + right +  "   " + heapSize );
        if (index != maxIndex) {
            T temp = heap.get(index);

            heap.set(index, heap.get(maxIndex));
            heap.set(maxIndex, temp);

            heapify(maxIndex);
        }

    }

    /**
     * Inserts the given data into heap.
     * Calls helper method heapIncreaseKey.
     * @param key the data to insert
     */
    public void insert(T key) {
        heapSize++;
        heap.add(key);
        heapIncreaseKey(heapSize - 1, key);
    }

    /**
     * Helper method for insert.
     * Bubbles an element up to its proper location
     * @param index the current index of the key
     * @param key the data
     */
    private void heapIncreaseKey(int index, T key) {
        if (cmp.compare(key, heap.get(index)) > 0) {
            heap.set(index, key);
        }

        while (index > 1 && cmp.compare(heap.get(getParent(index)), heap.get(index)) < 0) {
            T temp = heap.get(index);

            heap.set(index, heap.get(getParent(index)));
            heap.set(getParent(index), temp);

            index = getParent(index);
        }

    }

    /**
     * Removes the element at the specified index.
     * Calls helper method heapify
     * @param index the index of the element to remove
     */
    public void remove(int index) {
        if (index < 1 || index > heapSize) {
            throw new IndexOutOfBoundsException();
        }

        heap.set(index, heap.get(heapSize - 1));
        heap.remove(heapSize - 1);
        heapify(index);
        heapSize--;
    }

    /**Accessors*/

    /**
     * Returns the heap size (current number of elements)
     * @return the size of the heap
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Returns the location (index) of the
     * left child of the element stored at index.
     * @param index the current index
     * @return the index of the left child.
     * @precondition 0 < index <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getLeft(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > heapSize)
            throw new IndexOutOfBoundsException();

        return index * 2;
    }

    /**
     * Returns the location (index) of the right child of the element
     * stored at index.
     * @param index the current index
     * @return the index of the right child
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getRight(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > heapSize)
            throw new IndexOutOfBoundsException();

        return index * 2 + 1;
    }

    /**
     * Returns the location (index) of the
     * parent of the element stored at index.
     * @param index the current index
     * @return the index of the parent
     * @precondition 1 < index <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getParent(int index) throws IndexOutOfBoundsException {
        if (index <= 1 || index > heapSize)
            throw new IndexOutOfBoundsException();
        return index / 2;
    }

    /**
     * Returns the maximum element (highest priority)
     * @return the max value
     */
    public T getMax() {
        if(heap.isEmpty())
            return null;
        return heap.get(1);
    }

    /**
     * Returns the element at a specific index.
     * @param index an index in the heap.
     * @return the data at the index.
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public T getElement(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > heapSize)
			throw new IndexOutOfBoundsException();
		return heap.get(index);
    }

    /**Additional Operations*/

    /**
     * Creates a String of all elements in the heap, separated by ", ".
     * @return a String of all elements in the heap, separated by ", ".
     */
    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
		for (int i = 1; i < heapSize; i++) {
			ans.append(heap.get(i) + ", ");
		}
        ans.append(heap.get(heapSize));
		return ans.toString();
    }

    /**
     * Uses the heap sort algorithm to sort the heap into ascending order.
     * Calls helper method heapify.
     * @return an ArrayList of sorted elements
     * @postcondition heap remains a valid heap
     */
    public ArrayList<T> sort() {
        ArrayList<T> temp = new ArrayList<T>(heap);
        temp.remove(0);
        Heap<T> result = new Heap<T>(new ArrayList<T>(temp), cmp);

        for (int i = heapSize - 1; i >= 2; i--) {
            T cur = result.heap.get(1);

            result.heap.set(1, result.heap.get(i));
            result.heap.set(i, cur);

            result.heapSize--;

            result.heapify(1);
        }

        return result.heap;
    }
}
