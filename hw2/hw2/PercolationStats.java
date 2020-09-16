package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private double[] data;
    public PercolationStats(int N, int T, PercolationFactory pf) {
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
    }
    public double stddev() {
        return StdStats.stddev(data);
    }
    public double confidenceLow() {
        double mean = mean();
        double stddev = stddev();
        return mean - (1.96 * stddev) / Math.sqrt(data.length);
    }
    public double confidenceHigh() {
        double mean = mean();
        double stddev = stddev();
        return mean + (1.96 * stddev) / Math.sqrt(data.length);
    }

}
