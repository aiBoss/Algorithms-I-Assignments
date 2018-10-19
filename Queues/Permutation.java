/* *****************************************************************************
 *  Name: Mourya Karan Reddy (aiBoss)
 *  Date: 10/19/2018
 *  Description: Deques and Randomized Queues Assignment for Algorithms-I
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rs = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            rs.enqueue(StdIn.readString());
        for (int i = 0; i < k; i++)
            StdOut.println(rs.dequeue());

    }
}
