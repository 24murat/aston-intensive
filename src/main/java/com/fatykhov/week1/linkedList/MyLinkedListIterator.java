package com.fatykhov.week1.linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Realization of iterator for my custom linked list.
 * @author Murat Fatykhov
 *
 * @param <E> type of elements in linked list
 */
public class MyLinkedListIterator<E> implements Iterator<E> {
    private Node<E> current;

    /**
     * Constructs new iterator for linked list.
     * @param head of the linked list for which iterator will be constructed
     */
    public MyLinkedListIterator(Node<E> head) {
        this.current = head;
    }

    /**
     * Checks if iterator has next element.
     * @return true if iterator has next element
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Returns next element from linked list.
     * @return next element from linked list
     */
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        E item = current.item;
        current = current.next;
        return item;
    }
}
