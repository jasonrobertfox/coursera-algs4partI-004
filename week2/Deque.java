import java.util.NoSuchElementException;

public class Deque<Item> // implements Iterable<Item>
{
  public Deque()
  {
  }

  public boolean isEmpty()
  {
    return true;
  }

  public int size()
  {
    return 0;
  }

  public void addFirst(Item item)
  {
    validateAdd(item);
  }

  public void addLast(Item item)
  {
    validateAdd(item);
  }

  public void removeFirst()
  {
    validateRemove();
  }

  public void removeLast()
  {
    validateRemove();
  }

  // @Override
  // public Iterator<Item> iterator()
  // {
  // }

  private void validateAdd(Item item)
  {
    if (item == null) {
      throw new NullPointerException("Can not add a null item.");
    }
  }

  private void validateRemove()
  {
    if (size() == 0) {
      throw new NoSuchElementException("Nothing to remove, deque is empty.");
    }
  }

}
