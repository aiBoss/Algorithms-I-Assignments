/* *****************************************************************************
 *  Name: Mourya Karan Reddy (aiBoss)
 *  Date: 10/19/2018
 *  Description: Deques and Randomized Queues Assignment for Algorithms-I
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int size, n;

    // Constructor for resizing array.
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        size = 0;
        n = 0;
    }

    // To check if there are no items in the queue.
    public boolean isEmpty() {
        return n == 0;
    }

    // Number of items in the queue.
    public int size() {
        return n;
    }

    // To add an item at the end of queue.
    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.IllegalArgumentException("Please provide a valid item to add ");
        if (size == s.length) {
            resize(2 * s.length);
            size = n;
        }
        s[size++] = item;
        n++;
    }

    // to remove an item randomly.
    public Item dequeue() {
        Item item;
        if (isEmpty())
            throw new java.util.NoSuchElementException("No items to delete");
        int a = StdRandom.uniform(size);
        while (s[a] == null) {
            a = StdRandom.uniform(size);
        }
        item = s[a];
        s[a] = null;
        n--;
        if (n == s.length / 4) {
            resize(s.length / 2);
            size = n;
        }
        return item;
    }

    // Returns a sample item randomly from the queue.
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("No items to show");
        int a = StdRandom.uniform(size);
        while (s[a] == null) {
            a = StdRandom.uniform(size);
        }
        return s[a];
    }

    // method to resie the array.
    private void resize(int k) {
        int m;
        if (k == 0)
            m = 1;
        else
            m = k;
        Item[] copy = (Item[]) new Object[m];
        for (int i = 0, j = 0; i < size; j++) {
            while (i < size && s[i] == null)
                i++;
            if (i < size)
                copy[j] = s[i++];
        }
        s = copy;
    }

    // Iterator method.
    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    // Iterator Object class.
    private class RQIterator implements Iterator<Item> {
        private final Item[] x;
        private int k;

        public RQIterator() {
            x = (Item[]) new Object[n];
            k = n;
            for (int i = 0, j = 0; i < size; j++) {
                while (s[i] == null)
                    i++;
                x[j] = s[i++];
            }
            StdRandom.shuffle(x);
        }

        // Checks if there are any more items to be iterated.
        public boolean hasNext() {
            return k > 0;
        }

        // method to remove an item using iterator.
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Cannot remove item with iterator");
        }

        // method to return a random item as iterator.
        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("No items in Queue");
            return x[--k];
        }
    }

    public static void main(String[] args) {
        // empty method
    }
}
