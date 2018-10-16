/* *****************************************************************************
 *  Name: Mourya Karan Reddy (aiBoss)
 *  Date: 10/11/2018
 *  Description: Percolation Stats for Algorithms course from Princeton
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] m; // array to store the probabilities.
    private double mn;
    private double sd;

    // the constructor initializes the array and calculates the probabilities
    public PercolationStats(int n, int t) {
        if (n < 1 || t < 1)
            throw new java.lang.IllegalArgumentException("Enter Valid Lattice Size and Trials");
        this.m = new double[t];
        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int r = StdRandom.uniform(n) + 1;
                int c = StdRandom.uniform(n) + 1;
                if (!p.isOpen(r, c))
                    p.open(r, c);
            }
            m[i] = (double) p.numberOfOpenSites() / (double) (n * n);
        }
        this.mn = StdStats.mean(m);
        this.sd = StdStats.stddev(m);
    }

    // Calculates the mean of probabilities
    public double mean() {
        return mn;
    }

    // calculates the standard deviation
    public double stddev() {
        if (m.length == 1)
            return Double.NaN;
        else
            return sd;
    }

    // calculates the Lower Limit of 95% confidence interval
    public double confidenceLo() {
        return (mean() - 1.96 * stddev() / Math.sqrt((double) m.length));
    }

    // calculates the Upper Limit of 95% confidence interval
    public double confidenceHi() {
        return (mean() + 1.96 * stddev() / Math.sqrt((double) m.length));
    }

    // main method
    public static void main(String[] args) {
        int x = StdIn.readInt();
        int y = StdIn.readInt();
        PercolationStats ps = new PercolationStats(x, y);
        StdOut.println("mean                    =");
        StdOut.print(ps.mean());
        StdOut.println("\nstddev                  =");
        StdOut.print(ps.stddev());
        StdOut.println("\n95% confidence interval =");
        StdOut.printf("[ %d , %d ]", ps.confidenceLo(), ps.confidenceHi());
    }
}
