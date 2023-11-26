package com.fatykhov.week1.arrayList;

import com.fatykhov.week1.MyListInterface;

import java.util.Arrays;
import java.util.Iterator;

/**
 * My custom ArrayList realization.
 * @author Murat Fatykhov
 *
 * @param <E> type of elements in this array list
 */
public class MyArrayList<E> implements MyListInterface<E> {

    /**
     * Container's base (based on array).
     * */
    private E[] list;

    /**
     * Current list size.
     */
    private int size;

    /**
     * Default container capacity.
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs empty list with custom initial container capacity.
     *
     * @param customCapacity initial container capacity
     * @throws IllegalArgumentException if initial container capacity <= 0
     */
    public MyArrayList(int customCapacity) {
        if (customCapacity <= 0) {
            throw new IllegalArgumentException("Capacity should be greater than 0");
        } else {
            list = (E[]) new Object[customCapacity];
        }
    }

    /**
     * Constructs empty list with default initial container capacity = 10.
     */
    public MyArrayList() {
        list = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Adds item to the container at index = current list size and increases current list size by one.
     * First checks if container is already full. If yes, then expands it.
     * @see MyArrayList#checkForGrow()
     *
     * @param item element to add
     */
    @Override
    public void add(E item) {
        checkForGrow();
        list[size++] = item;
    }

    /**
     * Adds item to the container at custom index and increases current list size by one.
     * First checks if custom index is not out of bounds.
     * @see MyArrayList#checkIndex(int)
     *
     * Also checks if container is already full. If yes, then expands it.
     * @see MyArrayList#checkForGrow()
     *
     * @param index index which is used to add the element at
     * @param item element to add
     */
    @Override
    public void add(int index, E item) {
        checkIndex(index);
        checkForGrow();

        for (int i = size; i > index ; i--) {
            list[i] = list[i - 1];
        }

        list[index] = item;
        size++;
    }

    /**
     * Returns the current number of elements in list
     *
     * @return current number of elements in list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes element at transferred index from container and decreases current list size by one.
     * First checks if transferred index is not out of bounds.
     * @see MyArrayList#checkIndex(int)
     *
     * @param index index of the element to remove
     */
    @Override
    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }

        size--;
    }

    /**
     * Removes transferred element from container if it's presented and decreases current list size by one.
     * If not present exits the method.
     *
     * @see MyArrayList#index(Object)
     * @see MyArrayList#remove(int)
     * @param item element to remove
     */
    public void remove(E item) {
        int position = index(item);

        if (position < 0)
            return;

        remove(position);
    }

    /**
     * Returns index of transferred element. If not present returns -1
     * @param item element whose index to return
     * @return index of element in container if present, else -1
     */
    private int index(E item) {
        if (item == null)
            return -1;

        for (int i = 0; i < size; i++) {
            if (item.equals(list[i]))
                return i;
        }

        return -1;
    }

    /**
     * Returns element at transferred index from container.
     * @param index index of the element to return
     * @return element at transferred index from container
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return list[index];
    }

    /**
     * Replaces an old element at transferred index to a new transferred element.
     * First checks if transferred index is not out of bounds.
     * @see MyArrayList#checkIndex(int)
     *
     * @param index index at which element will be replaced
     * @param item new element which will replace an old element
     */
    public void set(int index, E item) {
        checkIndex(index);
        list[index] = item;
    }

    /**
     * Returns true if current list size is 0.
     *
     * @return true if current list size is 0
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the container and sets list size to 0.
     */
    @Override
    public void clear() {
        list = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Returns String representation of list.
     * @return String representation of list
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    /**
     * Checks if transferred index is not out of bounds. If yes, throws IndexOutOfBoundsException.
     *
     * @param index index to check
     * @throws IndexOutOfBoundsException if transferred index is out of list's bounds
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bounds");
    }

    /**
     * Checks if container is already full. If yes, then expands it by copying an old inner array and multiplying
     * its current size by 2.
     */
    private void checkForGrow() {
        if (size == list.length)
            list = Arrays.copyOf(list, size * 2);
    }

    /**
     * Returns new MyListIterator object based on this list.
     * @see MyArrayListIterator
     *
     * @return new MyListIterator object based on this list
     */
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<>(Arrays.copyOf(list, size));
    }

    /**
     * Sorts this list in natural order.
     */
    @Override
    public void sort() {
        if (size > 0) {
            Arrays.sort(list, 0, size);
        }
    }
}
