public class Percolation
{

  private static final int OPEN_FLAG = 1;
  private static final int CONNECTED_TO_BOTTOM_FLAG = 2;

  private int width;
  private int size;
  private int[] grid;
  private int topIndex;
  private QuickFindUF unionFind;
  private boolean percolates;

  public Percolation(int N)
  {
    // Set up the grid construct
    width = N;
    size = width * width;
    // TODO Try to get rid of this
    grid = new int[size + 1];
    grid[size] = 1;
    topIndex = size;
    unionFind = new QuickFindUF(size + 1);
  }

  public boolean percolates()
  {
    return percolates;
  }

  public boolean isOpen(int row, int col)
  {
    return isIndexOpen(indexFromRowCol(row, col));
  }

  public boolean isFull(int row, int col)
  {
    return unionFind.connected(indexFromRowCol(row, col), topIndex);
  }

  public void open(int row, int col)
  {
    int index = indexFromRowCol(row, col);
    grid[index] = OPEN_FLAG;

    // union to the bottom virtual site if in the bottom row
    if (index >= size - width) {
      connectToBottom(index);
    }

    // union to top virtual site if in the top row
    if (index < width) {
      joinSite(index, topIndex);
    }

    // union to top if top is open
    int top = index - width;
    if (top >= 0) {
      joinSite(index, top);
    }

    // union to below if below is open
    int below = index + width;
    if (below < size) {
      joinSite(index, below);
    }

    // union to right if right is open
    int right = index + 1;
    if (right % width != 0) {
      joinSite(index, right);
    }

    // union to left if left is open
    int left = index - 1;
    if (index % width != 0) {
      joinSite(index, left);
    }

    if (isConnectedToBottom(index) && unionFind.connected(index, topIndex)) {
      percolates = true;
    }

  }

  private void connectToBottom(int index)
  {
    grid[index] = CONNECTED_TO_BOTTOM_FLAG;
  }

  private boolean isConnectedToBottom(int index)
  {
    return grid[index] == CONNECTED_TO_BOTTOM_FLAG;
  }

  private int indexFromRowCol(int row, int col)
  {
    int index = width * (row - 1) + (col - 1);
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Coordinate out of bounds.");
    }
    return index;
  }

  private boolean isIndexOpen(int index)
  {
    return grid[index] > 0;
  }

  private void joinSite(int siteIndex, int neighborIndex)
  {
    if (isIndexOpen(neighborIndex)) {
      unionFind.union(siteIndex, neighborIndex);

      if (isConnectedToBottom(siteIndex) || isConnectedToBottom(neighborIndex)) {
        connectToBottom(siteIndex);
        connectToBottom(unionFind.find(siteIndex));
      }
    }
  }

}
