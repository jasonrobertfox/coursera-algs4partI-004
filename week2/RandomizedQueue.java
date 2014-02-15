import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{

  private int size = 0;
  private Item[] queue;
  private int first = 0;
  private int last = 0;
  private int fillable = 1;

  public RandomizedQueue()
  {
    queue = (Item[]) new Object[1];
  }

  public boolean isEmpty()
  {
    return size == 0;
  }

  public int size()
  {
    return size;
  }

  public void enqueue(Item item)
  {
    if (item == null) {
      throw new NullPointerException("Can not add a null item.");
    }
    if (fillable == 0) {
      doubleQueue();
    }
    queue[last++] = item;
    fillable--;
    size++;
  }

  public Item dequeue()
  {
    validateRemove();
    int choice = getRandomChoiceIndex();
    Item item = queue[choice];
    queue[choice] = null;
    size--;

    if (size > 0 && size == queue.length / 4) {
      halfQueue();
    }

    return item;
  }

  public Item sample()
  {
    validateRemove();
    return queue[getRandomChoiceIndex()];
  }

  @Override
  public Iterator<Item> iterator()
  {
    return new ArrayIterator();
  }

  private class ArrayIterator implements Iterator<Item>
  {
    private Item[] iteratorQueue;
    private int iteratorSize;

    public ArrayIterator()
    {
      iteratorQueue = (Item[]) new Object[size];
      iteratorSize = size;
      int j = 0;
      for (int i = 0; i < queue.length; i++) {
        if (queue[i] != null) {
          iteratorQueue[j++] = queue[i];
        }
      }
    }

    @Override
    public boolean hasNext()
    {
      return iteratorSize != 0;
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
      int choice = StdRandom.uniform(iteratorSize);
      while (iteratorQueue[choice] == null) {
        choice++;
      }
      Item item = iteratorQueue[choice];
      iteratorQueue[choice] = null;
      iteratorSize--;
      return item;
    }
  }

  private void halfQueue()
  {
    Item[] temp = (Item[]) new Object[queue.length / 2];
    int n = 0;
    for (int i = 0; i < queue.length; i++) {
      if (queue[i] != null) {
        temp[n++] = queue[i];
      }
    }
    queue = temp;
    last = size;
    fillable = size;
  }

  private void doubleQueue()
  {
    int newSize = 1;
    if (size != 0) {
      newSize = 2 * size;
    }
    Item[] temp = (Item[]) new Object[newSize];
    int n = 0;
    for (int i = 0; i < queue.length; i++) {
      if (queue[i] != null) {
        temp[n++] = queue[i];
      }
    }
    queue = temp;
    last = size;
    fillable = size;
  }

  private int getRandomChoiceIndex()
  {
    int choice = StdRandom.uniform(size);
    while (queue[choice] == null) {
      choice++;
    }
    return choice;
  }

  private void validateRemove()
  {
    if (isEmpty()) {
      throw new NoSuchElementException("Nothing to remove, deque is empty.");
    }
  }
}
