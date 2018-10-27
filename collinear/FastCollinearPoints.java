/* *****************************************************************************
 *  Name: Mourya Karan Reddy (aiBoss)
 *  Date: 10/25/2018
 *  Description: Collinear Points assignment - Algorithms-I from Princeton at Coursera.
 **************************************************************************** */

import java.util.Arrays;

public class FastCollinearPoints {

    private int n;             // number of line segments
    private final LineSegment[] ls; // Array of LineSegments

    // Constructor initializes the instance variables and counts the number of points
    public FastCollinearPoints(Point[] points) {

        if (points == null)
            throw new java.lang.IllegalArgumentException("Points array is null");
        else {
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null)
                    throw new java.lang.IllegalArgumentException("Point" + (i + 1) + "is null");
                for (int j = i + 1; j < points.length; j++)
                    if (points[j] != null && points[i].compareTo(points[j]) == 0)
                        throw new java.lang.IllegalArgumentException(
                                "Point" + (i + 1) + "is same as" + (j + 1));
            }
        }
        final Point[] p;   // Array to store points
        Point[] it;        // Arrray to sort points
        int size;    // Size of points array
        final Point[] low;       // Array of lo points of line segments.
        final Point[] high;      // Array of hi points of line segments.
        Point lo;          // Starting point of a Line Segment
        Point hi;          // End Point of Line Segment.
        size = points.length;           // initializing instance variables.
        p = new Point[size];
        it = new Point[size];
        low = new Point[size * (size - 1) / 2];
        high = new Point[size * (size - 1) / 2];

        for (int a = 0; a < size; a++) {
            p[a] = points[a];
            it[a] = points[a];
        }
        n = 0;
        for (int i = 0; i < size; i++) {        // Segment counter.
            Arrays.sort(it, 0, size, p[i].slopeOrder());   // Sort using comparator
            int counter
                    = 1;        // Counts number of points in linesegment other than invoking point
            double slope = p[i].slopeTo(it[0]);
            for (int j = 1; j < size; j++) {
                if (slope == p[i].slopeTo(it[j])) {
                    counter++;
                }
                if (j == size - 1 || slope != p[i].slopeTo(it[j])) {
                    if (counter > 2) {

                        if (j == size - 1 && slope == p[i].slopeTo(it[j])) {     // corner case
                            if (p[i].compareTo(it[j]) < 0) {
                                lo = p[i];
                                hi = it[j];
                            }
                            else {
                                lo = it[j];
                                hi = p[i];
                            }
                            for (int x = 1; x < counter; x++) {
                                if (lo.compareTo(it[j - x]) > 0)
                                    lo = it[j - x];
                                if (hi.compareTo(it[j - x]) < 0)
                                    hi = it[j - x];
                            }
                        }
                        else {
                            if (p[i].compareTo(it[j - 1]) < 0) {
                                lo = p[i];
                                hi = it[j - 1];
                            }
                            else {
                                lo = it[j - 1];
                                hi = p[i];
                            }
                            for (int x = 2; x <= counter; x++) {
                                if (lo.compareTo(it[j - x]) > 0)
                                    lo = it[j - x];
                                if (hi.compareTo(it[j - x]) < 0)
                                    hi = it[j - x];
                            }
                        }
                        low[n] = lo;
                        high[n] = hi;
                        n++;
                    }

                    if (j == size - 1)
                        continue;
                    counter = 1;
                    slope = p[i].slopeTo(it[j]);
                }
            }
        }

        final Point[] low2 = new Point[n];
        final Point[] high2 = new Point[n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (c == 0) {
                low2[c] = low[i];
                high2[c] = high[i];
                c++;
            }
            else
                for (int u = 0; u < c; u++) {
                    if (low[i].compareTo(low2[u]) == 0 && high[i].compareTo(high2[u]) == 0)
                        break;
                    if (u == c - 1) {
                        low2[c] = low[i];
                        high2[c] = high[i];
                        c++;
                    }
                }
        }
        n = c;
        ls = new LineSegment[n];
        for (int i = 0; i < n; i++)
            ls[i] = new LineSegment(low2[i], high2[i]);
    }


    // returns the Array of LineSegments
    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[n];
        for (int i = 0; i < n; i++)
            copy[i] = ls[i];
        return copy;
    }

    // returns number of line segments.
    public int numberOfSegments() {
        return n;
    }

    // Client
    public static void main(String[] args) {
        /*
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show(); */
    }
}


