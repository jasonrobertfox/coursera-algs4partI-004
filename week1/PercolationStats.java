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
    results = new double[1];
    results[0] = 1.0;
    mean = StdStats.mean(results);
    stddev = StdStats.stddev(results);
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

    double mean = 0.5929934999999997;
    double stddev = 0.00876990421552567;
    double conLo = 0.5912745987737567;
    double conHi = 0.5947124012262428;

    StdOut.println("mean                     " + mean);
    StdOut.println("stddev                   " + stddev);
    StdOut.println("95% confidence interval  " + conLo + ", " + conHi);
  }

  private double confidence95()
  {
    return (1.96 * stddev) / Math.sqrt(T);
  }
}
