/* *****************************************************************************
 *  Name: Mourya Karan Reddy (aiBoss)
 *  Date: 10/25/2018
 *  Description: Collinear Points assignment - Algorithms-I from Princeton at Coursera.
 **************************************************************************** */

public class BruteCollinearPoints {

    private int n;           // counter for line segments
    private final LineSegment[] ls;


    /* Constructor initializes points array and calculates number of line segments with 4 collinear points */
    public BruteCollinearPoints(Point[] points) {
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
        final Point[] p; // Array of points.
        final int size;  // size of array
        size = points.length;
        p = new Point[size];
        final Point[] low = new Point[size * (size - 1) / 2];
        final Point[] high = new Point[size * (size - 1) / 2];
        for (int a = 0; a < size; a++)
            p[a] = points[a];
        n = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    if (p[i].slopeTo(p[j]) != p[i].slopeTo(p[k]))
                        continue;
                    for (int m = k + 1; m < size; m++) {
                        if (p[i].slopeTo(p[j]) == p[i].slopeTo(p[m])) {
                            Point lo;
                            Point hi;
                            if (p[i].compareTo(p[j]) < 0) {
                                lo = p[i];
                                hi = p[j];
                            }
                            else {
                                lo = p[j];
                                hi = p[i];
                            }
                            if (lo.compareTo(p[k]) > 0)
                                lo = p[k];
                            if (lo.compareTo(p[m]) > 0)
                                lo = p[m];
                            if (hi.compareTo(p[k]) < 0)
                                hi = p[k];
                            if (hi.compareTo(p[m]) < 0)
                                hi = p[m];
                            low[n] = lo;
                            high[n] = hi;
                            n++;
                        }

                    }
                }
            }
        }
        ls = new LineSegment[n];
        for (int i = 0; i < n; i++)
            ls[i] = new LineSegment(low[i], high[i]);
    }

    // returns number of line segments
    public int numberOfSegments() {
        return n;
    }

    // Finds the line segments and creates an array of linesegments.
    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[n];
        for (int i = 0; i < n; i++)
            copy[i] = ls[i];
        return copy;
    }

    // client.
    public static void main(String[] args) {
        /* Empty Method
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();*/
    }
}
