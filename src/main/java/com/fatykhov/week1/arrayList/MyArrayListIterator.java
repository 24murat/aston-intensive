package com.fatykhov.week1.arrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Realization of iterator for my custom array list.
 * @author Murat Fatykhov
 *
 * @param <E> type of elements in list
 */
public class MyArrayListIterator<E> implements Iterator<E> {

    private int index = 0;
    private E[] list;

    /**
     * Constructs new iterator for array list.
     * @param list array list for which iterator will be constructed
     */
    public MyArrayListIterator(E[] list) {
        this.list = list;
    }

    /**
     * Checks if iterator has next element.
     * @return true if iterator has next element
     */
    @Override
    public boolean hasNext() {
        return index < list.length;
    }

    /**
     * Returns next element from array list.
     * @return next element from array list
     */
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list[index++];
    }

}
