import junit.framework.TestCase;

public class PercolationTest extends TestCase
{

  private Percolation p;
  private int size;

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
    assertNotOpen(2, 2);
    p.open(2, 2);
    assertOpen(2, 2);
  }

  public void testHandleExceptionForFirstOpenOutOfBound()
  {
    try {
      p.open(4, 0);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      assertTrue(e instanceof IndexOutOfBoundsException);
    }
  }

  public void testNotFullByDefault()
  {
    assertNotFull(1, 1);
  }

  public void testNotPercolatesByDefault()
  {
    assertNotPercolates();
  }

  // Throw away
  // public void testIsNotConnectedToBottomByDefault()
  // {
  // assertNotConnectedToBottom(1, 1);
  // }

  public void testPercolatesOnSingleSiteGrid()
  {
    p = new Percolation(1);
    p.open(1, 1);
    assertOpen(1, 1);
    assertFull(1, 1);
    // assertConnectedToBottom(1, 1);
    assertPercolates();
  }

  public void testSingleTopSite()
  {
    p = new Percolation(2);
    p.open(1, 1);
    // assertNotConnectedToBottom(1, 1);
    assertNotPercolates();
    // assertNotConnectedToBottom(2, 1);
  }

  public void testSingleBottomSite()
  {
    p = new Percolation(2);
    p.open(2, 1);
    // assertConnectedToBottom(2, 1);
    assertNotPercolates();
  }

  public void testTopThenBottomConnection()
  {
    p = new Percolation(2);
    p.open(1, 1);
    p.open(2, 1);
    assertPercolates();
    assertFull(2, 1);
    // assertConnectedToBottom(2, 1);
    // assertConnectedToBottom(1, 1);
  }

  public void testBottomThenTopConnection()
  {
    p = new Percolation(2);
    p.open(2, 1);
    p.open(1, 1);
    assertPercolates();
    assertFull(2, 1);
    // assertConnectedToBottom(2, 1);
    // assertConnectedToBottom(1, 1);
  }

  public void testEverythingBecomesConnectedToBottom()
  {
    p = new Percolation(6);
    p.open(6, 2);
    p.open(5, 2);
    p.open(2, 2);
    p.open(3, 2);
    // assertConnectedToBottom(5, 2);
    p.open(4, 2);
    // assertConnectedToBottom(2, 2);
  }

  public void testBackWashDoesNotOccur()
  {
    p.open(1, 1);
    p.open(3, 1);
    p.open(3, 3);
    p.open(2, 1);
    assertPercolates();
    assertNotFull(3, 3);
  }

  public void testLeftConnection()
  {
    p.open(2, 1);
    p.open(3, 1);
    // assertConnectedToBottom(2, 1);
    p.open(2, 2);
    // assertConnectedToBottom(2, 2);
    p.open(1, 2);
    // assertConnectedToBottom(1, 2);
    assertPercolates();
  }

  public void testRightConnection()
  {
    p.open(2, 3);
    p.open(3, 3);
    // assertConnectedToBottom(2, 3);
    p.open(2, 2);
    // assertConnectedToBottom(2, 2);
    p.open(1, 2);
    // assertConnectedToBottom(1, 2);
    assertPercolates();
  }

  public void testMiddleTopAndBottomConnecting()
  {
    p.open(1, 1);
    p.open(3, 1);
    p.open(2, 1);
    assertFull(3, 1);
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
    p = new Percolation(5);
    p.open(1, 5);
    p.open(2, 4);
    p.open(2, 5);
    p.open(5, 2);
    p.open(4, 3);
    p.open(4, 2);
    p.open(2, 2);
    p.open(3, 4);
    p.open(3, 3);
    assertPercolates();
  }

  // Helper methods
  // ////////////////////////////////////////////////////////////////////////////////

  private void assertOpen(int row, int col)
  {
    assertTrue(p.isOpen(row, col));
  }

  private void assertNotOpen(int row, int col)
  {
    assertFalse(p.isOpen(row, col));
  }

  private void assertNotFull(int row, int col)
  {
    assertFalse(p.isFull(row, col));
  }

  private void assertFull(int row, int col)
  {
    assertTrue(p.isFull(row, col));
  }

  private void assertNotPercolates()
  {
    assertFalse(p.percolates());
  }

  private void assertPercolates()
  {
    assertTrue(p.percolates());
  }

  // // Throw away
  // private void assertNotConnectedToBottom(int i, int j)
  // {
  // assertFalse(p.connectedToBottom(i, j));
  // }
  //
  // // Throw away
  // private void assertConnectedToBottom(int i, int j)
  // {
  // assertTrue(p.connectedToBottom(i, j));
  // }

}
