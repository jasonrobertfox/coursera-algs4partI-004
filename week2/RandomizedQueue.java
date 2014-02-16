import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{

  private int size = 0;
  private Item[] queue;
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
      adjustQueue();
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
      adjustQueue();
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
    private int nextPointer;

    public ArrayIterator()
    {
      nextPointer = size - 1;
      iteratorQueue = (Item[]) new Object[size];
      int j = 0;
      for (int i = 0; i < last; i++) {
        if (queue[i] != null) {
          iteratorQueue[j++] = queue[i];
        }
      }
      StdRandom.shuffle(iteratorQueue);
    }

    @Override
    public boolean hasNext()
    {
      return nextPointer >= 0;
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
      Item item = iteratorQueue[nextPointer];
      iteratorQueue[nextPointer--] = null;
      return item;
    }
  }

  private void adjustQueue()
  {
    int newSize = 1;
    fillable = 1;
    if (size != 0) {
      newSize = 2 * size;
      fillable = size;
    }
    Item[] temp = (Item[]) new Object[newSize];
    int n = 0;
    for (int i = 0; i < last; i++) {
      if (queue[i] != null) {
        temp[n++] = queue[i];
      }
    }
    queue = temp;
    last = size;
  }

  private int getRandomChoiceIndex()
  {
    int choice = StdRandom.uniform(last);
    while (queue[choice] == null) {
      if (choice == queue.length - 1) {
        choice = 0;
      } else {
        choice++;
      }
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
