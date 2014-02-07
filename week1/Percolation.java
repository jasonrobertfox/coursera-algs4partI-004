public class Percolation
{

  private static final int OPEN_FLAG = 1;
  private static final int BOTTOM_FLAG = 2;

  private int[] sites;
  private int width;
  private int size;
  private WeightedQuickUnionUF unionFind;
  private boolean percolates;

  public Percolation(int N)
  {
    width = N;
    size = width * width;
    sites = new int[size];
    unionFind = new WeightedQuickUnionUF(size + 1);
  }

  public void open(int i, int j)
  {
    int siteIndex = toIndex(i, j);
    sites[siteIndex] = OPEN_FLAG;

    // union to top virtual site if in the top row
    if (siteIndex < width) {
      connectSites(siteIndex, virtualSiteIndex());
    }

    // update the nodes if the site is in the bottom row
    if (siteIndex >= size - width) {
      connectToBottom(siteIndex);
    }

    // try and connect to a above neighbor
    int above = siteIndex - width;
    if (above >= 0) {
      connectNeighbor(siteIndex, above);
    }

    // try and connect to a below neighbor
    int below = siteIndex + width;
    if (below < size) {
      connectNeighbor(siteIndex, below);
    }

    // try and connect to a left neighbor
    int left = siteIndex - 1;
    if (siteIndex % width != 0) {
      connectNeighbor(siteIndex, left);
    }

    // try and connect to a right neighbor
    int right = siteIndex + 1;
    if (right % width != 0) {
      connectNeighbor(siteIndex, right);
    }

    // if the site is now connected to the bottom and to the top, percolate!
    if (connectedToBottom(siteIndex) && connectedToTop(siteIndex)) {
      percolates = true;
    }

  }

  public boolean isOpen(int i, int j)
  {
    return siteOpen(toIndex(i, j));
  }

  public boolean isFull(int i, int j)
  {
    return connectedToTop(toIndex(i, j));
  }

  public boolean percolates()
  {
    return percolates;
  }

  private boolean connectedToTop(int siteIndex)
  {
    return unionFind.connected(siteIndex, virtualSiteIndex());
  }

  private int toIndex(int row, int col)
  {
    if (row <= 0 || col <= 0 || row > width || col > width) {
      throw new IndexOutOfBoundsException("Coordinate out of bounds.");
    }
    int index = width * (row - 1) + (col - 1);
    return index;
  }

  private int virtualSiteIndex()
  {
    return size;
  }

  private void connectSites(int siteIndex, int neighborIndex)
  {
    unionFind.union(siteIndex, neighborIndex);
  }

  private void connectToBottom(int siteIndex)
  {
    sites[siteIndex] = BOTTOM_FLAG;
    sites[unionFind.find(siteIndex)] = BOTTOM_FLAG;
  }

  private boolean connectedToBottom(int siteIndex)
  {
    return sites[unionFind.find(siteIndex)] == BOTTOM_FLAG
        || sites[siteIndex] == BOTTOM_FLAG;
  }

  private boolean siteOpen(int siteIndex)
  {
    return sites[siteIndex] > 0;
  }

  private void connectNeighbor(int siteIndex, int neighborIndex)
  {
    if (siteOpen(neighborIndex)) {
      connectSites(siteIndex, neighborIndex);
      if (connectedToBottom(neighborIndex) || connectedToBottom(siteIndex)) {
        connectToBottom(neighborIndex);
        connectToBottom(siteIndex);
      }
    }
  }

}
