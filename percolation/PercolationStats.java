import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private final double[] x;
    private final int trials;
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("n and trials must larger than 0");
        }
        this.trials = trials;
        x = new double[trials];
        for (int t = 0;t < trials; t++){
            Percolation perc = new Percolation(n);
            int openSites = 0;

            while(!perc.percolates()){
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    openSites++;
                }
            }
            x[t] = (double)openSites / (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(x);

    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        double x_mean = mean();
        double x_stddev = stddev();
        return x_mean - 1.96*x_stddev / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        double x_mean = mean();
        double x_stddev = stddev();
        return x_mean + 1.96*x_stddev / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n,trials);
        System.out.println("Mean = " + stats.mean());
        System.out.println("Stddev = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
