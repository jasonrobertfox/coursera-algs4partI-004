import junit.framework.TestCase;

/**
 * A JUnit test case class. Every method starting with the word "test" will be
 * called when running the test with JUnit.
 */
public class PercolationTest extends TestCase
{

  private int size;
  private Percolation p;

  @Override
  protected void setUp() throws Exception
  {
    size = 3;
    p = new Percolation(size);
  }

  public void testInitialize()
  {
    assertTrue(p instanceof Percolation);
  }

  public void testAllSitesShouldBeNotOpenByDefault()
  {
    for (int row = 1; row <= size; row++) {
      for (int col = 1; col <= size; col++) {
        assertFalse(p.isOpen(row, col));
      }
    }
  }

  public void testCanOpenASite()
  {
    assertFalse(p.isOpen(2, 2));
    p.open(2, 2);
    assertTrue(p.isOpen(2, 2));
  }

  public void testNotFullByDefault()
  {
    assertNotFull(1, 1);
  }

  public void testHandleExceptionForFirstOpenOutOfBound()
  {
    try {
      p.open(4, 2);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      assertTrue(e instanceof IndexOutOfBoundsException);
    }
  }

  public void testOpenTopSiteIsFull()
  {
    p.open(1, 1);
    assertTrue(p.isOpen(1, 1));
    assertFull(1, 1);
  }

  public void testOpenBottomSiteIsNotFull()
  {
    p.open(3, 1);
    assertNotFull(1, 1);
  }

  public void testPercolatesOnSmallGrid()
  {
    Percolation ptest = new Percolation(1);
    ptest.open(1, 1);
    assertTrue(ptest.isOpen(1, 1));
    assertTrue(ptest.isFull(1, 1));
    assertTrue(ptest.percolates());
  }

  public void testTwoConnectedSitesAreFull()
  {
    p.open(2, 1);
    p.open(1, 1);
    assertFull(2, 1);
  }

  public void testConnectingUpFromBottomRow()
  {
    Percolation ptest = new Percolation(2);
    ptest.open(1, 1);
    ptest.open(2, 1);
    assertTrue(ptest.isFull(2, 1));
    assertTrue(ptest.percolates());
  }

  public void testMiddleTopAndBottomConnecting()
  {
    p.open(1, 1);
    p.open(3, 1);
    p.open(2, 1);
    assertFull(3, 1);
    assertPercolates();
  }

  public void testRightConnecting()
  {
    p.open(1, 2);
    p.open(2, 2);
    p.open(3, 1);
    assertNotPercolates();
    assertNotFull(3, 1);
    p.open(2, 1);
    assertFull(2, 1);
    assertPercolates();
  }

  public void testLeftConnecting()
  {
    p.open(1, 2);
    p.open(2, 2);
    p.open(3, 3);
    assertNotPercolates();
    assertNotFull(3, 3);
    p.open(2, 3);
    assertFull(2, 3);
    assertPercolates();
  }

  public void testStrangeConnection()
  {
    p.open(3, 1);
    p.open(2, 2);
    p.open(1, 3);
    p.open(3, 2);
    p.open(2, 3);
    assertPercolates();
  }

  public void testComplicatedPercolation()
  {
    Percolation ptest = new Percolation(5);
    int[][] steps = { { 1, 5 }, { 2, 4 }, { 2, 5 }, { 5, 2 }, { 4, 3 },
        { 4, 2 }, { 2, 2 }, { 3, 4 }, { 3, 3 } };
    for (int[] step : steps) {
      ptest.open(step[0], step[1]);
    }
    assertTrue(ptest.percolates());
  }

  public void testThatNoBackwashHappens()
  {
    p.open(1, 1);
    p.open(2, 1);
    p.open(3, 3);
    assertNotPercolates();
    assertNotFull(3, 3);
    p.open(3, 1);
    assertPercolates();
    assertNotFull(3, 3);
  }

  private void assertPercolates()
  {
    assertTrue(p.percolates());
  }

  private void assertNotPercolates()
  {
    assertFalse(p.percolates());
  }

  private void assertNotFull(int row, int col)
  {
    assertFalse(p.isFull(row, col));
  }

  private void assertFull(int row, int col)
  {
    assertTrue(p.isFull(row, col));
  }
}
