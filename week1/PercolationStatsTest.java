import junit.framework.TestCase;

public class PercolationStatsTest extends TestCase
{

  public void testHandleArgumentExceptionForInvalidN()
  {
    try {
      new PercolationStats(0, 1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
  }

  public void testHandleArgumentExceptionForInvalidT()
  {
    try {
      new PercolationStats(1, 0);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e instanceof IllegalArgumentException);
    }
  }

  public void testReturnOneForSmallestTest()
  {
    PercolationStats ps = new PercolationStats(1, 1);
    assertEquals(1.0, ps.mean());
    assertEquals(Double.NaN, ps.stddev());
    assertEquals(Double.NaN, ps.confidenceLo());
    assertEquals(Double.NaN, ps.confidenceHi());
  }

}
