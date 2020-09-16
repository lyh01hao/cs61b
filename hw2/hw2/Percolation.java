package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF quTop;
    private WeightedQuickUnionUF quTopToBottom;
    private int grid[];
    private int N;
    private int numOfOpen;
    private int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public Percolation(int N) {               // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.quTop = new WeightedQuickUnionUF(N * N + 1);
        this.quTopToBottom = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            quTop.union(i, N * N);
            quTopToBottom.union(i, N * N);
            quTopToBottom.union(indexTo1D(N - 1, i), N * N + 1);
        }
        grid = new int[N*N];
        this.numOfOpen = 0;

    }
    public void open(int row, int col) {      // open the site (row, col) if it is not open already}
        if (!validate(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        for (int[] d : direction) {
            if (validate(row + d[0], col + d[1]))
            if (isOpen(row + d[0], col + d[1])) {
                quTop.union(indexTo1D(row, col), indexTo1D(row + d[0], col + d[1]));
                quTopToBottom.union(indexTo1D(row, col), indexTo1D(row + d[0], col + d[1]));
            }
        }
        numOfOpen++;
        grid[indexTo1D(row, col)] = 1;
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        if (!validate(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[indexTo1D(row,col)] == 1;
    }

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        if (!validate(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return isOpen(row, col) && quTop.connected(indexTo1D(row, col), N * N);
    }
    public int numberOfOpenSites() {          // number of open sites
        return numOfOpen;
    }
    public boolean percolates() {          // does the system percolate?
        return quTopToBottom.connected(N * N + 1, N * N);
    }
    public static void main(String[] args) {  // use for unit testing (not required)
        new Percolation(5);
    }
    private int indexTo1D(int row, int col) {
        return row * N + col;
    }
    private boolean validate(int row, int col) {
        return (row >= 0 && row < N && col >= 0 && col < N);
    }
}

