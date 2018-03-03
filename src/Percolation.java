import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qf;

    public Percolation(int n)           // create n-by-n grid, with all sites blocked
    {
        size = n;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        opened[row - 1][col - 1] = true;
        if (row == 1) {
            qf.union(getQFIndex(row, col), top);
        }
        if (row == size) {
            qf.union(getQFIndex(row, col), bottom);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row - 1, col));
        }
        if (row < size && isOpen(row + 1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row + 1, col));
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return opened[row - 1][col - 1];
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (0 < row && row <= size && 0 < col && col <= size) {
            return qf.connected(top, getQFIndex(row, col));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return 0;
    }
    public boolean percolates()              // does the system percolate?
    {
        return qf.connected(top, bottom);
    }
    private int getQFIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    public static void main(String[] args)   // test client (optional)
    {

    }
}
