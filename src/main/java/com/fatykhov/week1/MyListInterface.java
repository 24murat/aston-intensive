package com.fatykhov.week1;

/**
 * Common interface for my custom lists realizations.
 * @author Murat Fatykhov
 *
 * @param <E> type of elements in list
 */
public interface MyListInterface<E> extends Iterable<E> {
    void add(E item);
    void add(int index, E item);
    E get(int index);
    void remove(int index);
    int size();
    boolean isEmpty();
    void clear();
    void sort();
}
