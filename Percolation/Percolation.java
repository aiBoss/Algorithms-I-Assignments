/* *****************************************************************************
 *  Name: Mourya Karan Reddy (aiBoss)
 *  Date: 10/11/2018
 *  Description: Percolation Assignment for Algorithms course from Princeton.
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size, openCount = 0;
    // size is the size of the matrix, openCount is the counter for open sites
    private boolean[][] mat;             // matrix variable
    private WeightedQuickUnionUF p, q;

    /* Constructor initilizes all the elements of the matrix to be blocked and creates a Weighted Quick Union-Find data type */
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("N must be atleast 1");
        }
        this.size = n;
        this.mat = new boolean[n][n];
        this.p = new WeightedQuickUnionUF(n * n + 2);
        this.q = new WeightedQuickUnionUF(n * n + 1);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.mat[i][j] = false;

    }

    // Open method opens an element at given coordinates and connects it to adjacent open sites
    public void open(int r, int c) {
        if (r < 1 || c < 1 || r > size || c > size)
            throw new java.lang.IllegalArgumentException("row and column are not in range");
        if (!isOpen(r, c)) {
            mat[r - 1][c - 1] = true;
            openCount++;
            if (r == 1) {
                p.union(0, (r - 1) * size + c);
                q.union(0, (r - 1) * size + c);
            }

            if (r == size)
                p.union(size * size + 1, (r - 1) * size + c);
        }
        if (c - 1 > 0 && isOpen(r, c - 1)) {
            p.union((r - 1) * size + c, (r - 1) * size + c - 1);
            q.union((r - 1) * size + c, (r - 1) * size + c - 1);
        }
        if (c + 1 <= size && isOpen(r, c + 1)) {
            p.union((r - 1) * size + c, (r - 1) * size + c + 1);
            q.union((r - 1) * size + c, (r - 1) * size + c + 1);
        }
        if (r - 1 > 0 && isOpen(r - 1, c)) {
            p.union((r - 1) * size + c, (r - 2) * size + c);
            q.union((r - 1) * size + c, (r - 2) * size + c);
        }
        if (r + 1 <= size && isOpen(r + 1, c)) {
            p.union((r - 1) * size + c, r * size + c);
            q.union((r - 1) * size + c, r * size + c);
        }

    }

    // isOpen method checks whether a givem site is open or blocked
    public boolean isOpen(int r, int c) {
        if (r < 1 || c < 1 || r > size || c > size)
            throw new java.lang.IllegalArgumentException("row and column are not in range");
        return mat[r - 1][c - 1];
    }

    // isFull method check whether given element is connected to top or not
    public boolean isFull(int r, int c) {
        if (r < 1 || c < 1 || r > size || c > size)
            throw new java.lang.IllegalArgumentException("row and column are not in range");

        return q.connected(0, (r - 1) * size + c);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // checks whether the system percolates or not
    public boolean percolates() {
        return p.connected(0, size * size + 1);
    }

    public static void main(String[] args) {

    }
}
