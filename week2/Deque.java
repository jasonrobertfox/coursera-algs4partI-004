import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
  private int size = 0;
  private Node<Item> first;
  private Node<Item> last;

  private class Node<Item>
  {
    private Item item;
    private Node<Item> next;
    private Node<Item> prev;
  }

  public Deque()
  {
    first = null;
    last = null;
  }

  public boolean isEmpty()
  {
    return size == 0;
  }

  public int size()
  {
    return size;
  }

  public void addFirst(Item item)
  {
    validateAdd(item);

    Node<Item> newFirst = new Node<Item>();
    newFirst.item = item;
    Node<Item> oldFirst = first;
    first = newFirst;
    first.next = oldFirst;
    if (oldFirst == null) {
      last = newFirst;
    }
    size++;
  }

  public void addLast(Item item)
  {
    validateAdd(item);
    Node<Item> newLast = new Node<Item>();
    newLast.item = item;
    Node<Item> oldLast = last;
    last = newLast;
    last.prev = oldLast;
    if (oldLast == null) {
      first = newLast;
    }
    size++;
  }

  public Item removeFirst()
  {
    validateRemove();
    Item item = first.item;
    first = first.next;
    size--;
    return item;
  }

  public Item removeLast()
  {
    validateRemove();
    Item item = last.item;
    last = last.prev;
    size--;
    return item;
  }

  @Override
  public Iterator<Item> iterator()
  {
    return new ListIterator<Item>(first);
  }

  private class ListIterator<Item> implements Iterator<Item>
  {
    private Node<Item> current;

    public ListIterator(Node<Item> first)
    {
      current = first;
    }

    @Override
    public boolean hasNext()
    {
      return current != null;
    }

    @Override
    public void remove()
    {
      throw new UnsupportedOperationException();
    }

    @Override
    public Item next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  private void validateAdd(Item item)
  {
    if (item == null) {
      throw new NullPointerException("Can not add a null item.");
    }
  }

  private void validateRemove()
  {
    if (isEmpty()) {
      throw new NoSuchElementException("Nothing to remove, deque is empty.");
    }
  }

}
