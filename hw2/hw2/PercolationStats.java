package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private double[] data;
    public PercolationStats(int N, int T, PercolationFactory pf) {  // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        data = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);

            while (!p.percolates()) {
                int r = StdRandom.uniform(N);
                int c = StdRandom.uniform(N);
                p.open(r, c);
            }
            data[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(data);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(data);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double mean = mean();
        double stddev = stddev();
        return mean - (1.96 * stddev) / Math.sqrt(data.length);
    }                               // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mean = mean();
        double stddev = stddev();
        return mean + (1.96 * stddev) / Math.sqrt(data.length);
    }                                // high endpoint of 95% confidence interval
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(30, 100, new PercolationFactory());
        System.out.println("Low bound: " + ps.confidenceLow());
        System.out.println("High bound: " + ps.confidenceHigh());
    }
}
