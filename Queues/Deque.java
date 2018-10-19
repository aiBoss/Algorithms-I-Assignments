/* *****************************************************************************
 *  Name: Mourya Karan Reddy (aiBoss)
 *  Date: 10/17/2018
 *  Description: Deques and Randomized Queues Assignment for Algorithms-I
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;

    // Double Ended Node.
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // Constructor to initialize empty Deque.
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // method to check if Deque is empty.
    public boolean isEmpty() {
        return first == null;
    }

    // method to find number of items.
    public int size() {
        return size;
    }

    // Method to add an Item at First.
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("No Item provided to add");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (last == null) {
            last = first;
        }
        else {
            oldfirst.previous = first;
        }
        size++;
    }

    // Method to add an Item at last.
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("No Item provided to add");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        size++;
    }

    // Method to remove First Item.
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No Items in Deque to remove");
        }
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        else {
            first.previous = null;
        }
        size--;
        return item;
    }

    // Method to remove last item.
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No Items in Deque to remove");
        }
        Item item = last.item;
        last = last.previous;
        if (last == null) {
            first = null;
        }
        else {
            last.next = null;
        }
        size--;
        return item;
    }

    // Implementing iterator method of Iterable Interface.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // Class for returning Iteraror object.
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Cannot be removed");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more items to return");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        // Empty method.
    }
}
