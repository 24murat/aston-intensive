package com.fatykhov.week1.linkedList;

import com.fatykhov.week1.MyListInterface;

import java.util.Iterator;

/**
 * My custom LinkedList realization.
 * @author Murat Fatykhov
 *
 * @param <E> type of elements in this linked list
 */
public class MyLinkedList<E> implements MyListInterface<E> {
    /**
     * First node of this linked list.
     */
    private Node<E> head;

    /**
     * Last node of this linked list.
     */
    private Node<E> tail;

    /**
     * Size of this linked list.
     */
    private int size;

    /**
     * Constructs empty linked list.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Adds element to the beginning of this linked list.
     * @param item element to add to the beginning of this linked list
     */
    public void addFirst(E item) {
        Node<E> temp = new Node<>(item);

        if (isEmpty()) {
            tail = temp;
        } else {
            head.prev = temp;
        }

        temp.next = head;
        head = temp;

        size++;
    }

    /**
     * Adds element to the end of this linked list.
     * @param item element to add to the end of this linked list
     */
    @Override
    public void add(E item) {
        Node<E> temp = new Node<>(item);

        if (isEmpty()) {
            head = temp;
        } else {
            tail.next = temp;
        }

        temp.prev = tail;
        tail = temp;

        size++;
    }

    /**
     * Adds element at transferred index of this linked list.
     * Also checks if custom index is not out of bounds.
     * @see MyLinkedList#checkIndex(int)
     *
     * @param index index which is used to add the element at
     * @param item element to add
     */
    @Override
    public void add(int index, E item) {
        Node<E> temp = new Node<>(item);

        if (isEmpty()) {
            head = temp;
            tail = temp;
            size++;
            return;
        }

        checkIndex(index);

        if (index == 0) {
            addFirst(item);
            return;
        }

        Node<E> current = getNode(index);

        current.prev.next = temp;
        temp.prev = current.prev;
        current.prev = temp;
        temp.next = current;

        size++;
    }

    /**
     * Returns node at transferred index from this linked list.
     *
     * @param index index of node to return
     * @return node at transferred index from this linked list
     */
    private Node<E> getNode(int index) {
        checkIndex(index);

        Node<E> current;

        if (index <= size / 2) {
            current = head;
            int c = 0;

            while (c != index) {
                current = current.next;
                c++;
            }
        } else {
            current = tail;
            int c = size - 1;

            while (c != index) {
                current = current.prev;
                c--;
            }
        }
        return current;
    }

    /**
     * Returns element at transferred index from this linked list.
     * @param index index of element to return
     * @return element at transferred index from this linked list
     */
    @Override
    public E get(int index) {
        return getNode(index).item;
    }

    /**
     * Removes first element of this linked list.
     */
    public void removeFirst() {
        if (isEmpty()) {
            return;
        } else if (head.next == null) {
            tail = null;
        } else {
            head.next.prev = null;
        }
        head = head.next;

        size--;
    }

    /**
     * Removes last element of this linked list.
     */
    public void removeLast() {
        if (isEmpty()) {
            return;
        } else if (head.next == null) {
            head = null;
        } else {
            tail.prev.next = null;
        }
        tail = tail.prev;

        size--;
    }

    /**
     * Removes element at transferred index from this linked list.
     * Also checks if custom index is not out of bounds.
     * @see MyLinkedList#checkIndex(int)
     *
     * @param index index to remove
     */
    @Override
    public void remove(int index) {
        if (isEmpty()) {
            return;
        }

        checkIndex(index);

        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<E> current = getNode(index);
            current.prev.next = current.next;
            current.next.prev = current.prev;

            size--;
        }
    }

    /**
     * Returns current linked list size.
     * @return current linked list size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if this linked list is empty.
     * @return true if this linked list is empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes all elements from this linked list.
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    /**
     * Sorts this linked list (selection sort).
     */
    @Override
    public void sort() {
        for (int step = 0; step < size; step++) {
            int minIndex = findMinElement(step);
            swapNodes(getNode(step), getNode(minIndex));
        }
    }

    /**
     * Finds minimal element in this linked list when starting from transferred index.
     *
     * @param start index from which searching for minimal element
     * @return index of minimal element in specified area of this linked list
     */
    private int findMinElement(int start) {
        int minIndex = start;
        E minElement = get(start);

        for (int i = start + 1; i < size; i++) {
            if (((Comparable<E>) this.get(i)).compareTo(minElement) < 0) {
                minElement = get(i);
                minIndex = i;
            }
        }

        return minIndex;
    }

    /**
     * Swaps transferred nodes.
     *
     * @param n1 first node
     * @param n2 second node
     */
    private void swapNodes(Node<E> n1, Node<E> n2) {
        if (n1 == null || n2 == null || n1 == n2) {
            return;
        }

        E temp = n1.item;
        n1.item = n2.item;
        n2.item = temp;
    }

    /**
     * Checks if transferred index is not out of bounds. If yes, throws IndexOutOfBoundsException.
     *
     * @param index index to check
     * @throws IndexOutOfBoundsException if transferred index is out of list's bounds
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index:" + index + " is out of bounds:" + size);
    }

    /**
     * Returns new MyLinkedListIterator object for this linked list.
     * @return new MyLinkedListIterator object for this linked list
     */
    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator<>(head);
    }
}
