package com.fatykhov.week1.linkedList;

/**
 * Node realization which objects contain element itself and links to the next and previous elements.
 * @author Murat Fatykhov
 *
 * @param <E> type of element
 */
public class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    /**
     * Construct node which contains transferred element.
     *
     * @param item which will be contained inside node
     */
    public Node(E item) {
        this.item = item;
    }

}
