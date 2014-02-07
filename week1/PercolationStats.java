public class PercolationStats
{
  private double[] results;
  private double mean;
  private double stddev;
  private int T;
  private int N;

  public PercolationStats(int N, int T)
  {
    if (N < 1 || T < 1) {
      throw new IllegalArgumentException("N and T must be greater than 0.");
    }
    this.T = T;
    this.N = N;
    results = new double[T];
    int totalSites = N * N;
    runPercolation(totalSites);
    mean = StdStats.mean(results);
    stddev = StdStats.stddev(results);
  }

  private void runPercolation(int totalSites)
  {
    for (int i = 0; i < T; i++) {
      // Create a new percolation and set the open sites counter to zero
      Percolation p = new Percolation(N);
      int openSites = 0;

      while (!p.percolates()) {
        int row = StdRandom.uniform(N) + 1;
        int col = StdRandom.uniform(N) + 1;
        if (!p.isOpen(row, col)) {
          p.open(row, col);
          openSites++;
        }
      }
      // Add the ratio of open sites to total sites to the results
      results[i] = (double) openSites / (double) totalSites;
    }
  }

  public double mean()
  {
    return mean;
  }

  public double stddev()
  {

    return stddev;
  }

  public double confidenceLo()
  {
    return mean - confidence95();
  }

  public double confidenceHi()
  {
    return mean + confidence95();
  }

  public static void main(String[] args)
  {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);

    PercolationStats stats = new PercolationStats(N, T);
    StdOut.println("mean                     " + stats.mean());
    StdOut.println("stddev                   " + stats.stddev());
    StdOut.println("95% confidence interval  " + stats.confidenceLo() + ", "
        + stats.confidenceHi());
  }

  private double confidence95()
  {
    return (1.96 * stddev) / Math.sqrt(T);
  }
}
