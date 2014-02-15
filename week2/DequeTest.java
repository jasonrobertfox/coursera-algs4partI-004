import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

public class DequeTest extends TestCase
{

  private Deque<String> d;

  @Override
  protected void setUp() throws Exception
  {
    d = new Deque<String>();
  }

  public void testNewDequeIsEmpty() throws Exception
  {
    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }

  public void testHandleExceptionForAddFirstNull() throws Exception
  {
    try {
      d.addFirst(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      assertTrue(e instanceof NullPointerException);
    }
  }

  public void testHandleExceptionForAddLastNull() throws Exception
  {
    try {
      d.addLast(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      assertTrue(e instanceof NullPointerException);
    }
  }

  public void testHandleExceptionForRemoveFirstFromEmpty() throws Exception
  {
    try {
      d.removeFirst();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

  public void testHandleExceptionForRemoveLastFromEmpty() throws Exception
  {
    try {
      d.removeLast();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

  public void testCanAddFirstOneItem() throws Exception
  {
    d.addFirst("test");
    assertEquals(1, d.size());
  }

  public void testCanRemoveFirstOneItem() throws Exception
  {
    d.addFirst("test");
    assertEquals("test", d.removeFirst());
    assertEquals(0, d.size());
  }

  public void testCanRemoveLastOneItem() throws Exception
  {
    d.addFirst("test");
    assertEquals(1, d.size());
    assertEquals("test", d.removeLast());
    assertEquals(0, d.size());
  }

  public void testCanAddLastOneItem() throws Exception
  {
    d.addLast("test");
    assertEquals(1, d.size());
    assertEquals("test", d.removeLast());
    assertEquals(0, d.size());
  }

  public void testCanAddLastAndRemoveFirst() throws Exception
  {
    d.addLast("test");
    assertEquals(1, d.size());
    assertEquals("test", d.removeFirst());
    assertEquals(0, d.size());
  }

  public void testAddSomeRemoveSome() throws Exception
  {
    d.addLast("a");
    d.addFirst("b");
    d.addLast("c");
    d.removeFirst();
    d.addFirst("d");
    d.removeLast();
    d.addLast("e");
    d.removeFirst();
    assertEquals("e", d.removeLast());
  }

  public void testExceptionOnRemoveMethod() throws Exception
  {
    d.addFirst("foo");
    Iterator<String> i = d.iterator();
    try {
      i.remove();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertTrue(e instanceof UnsupportedOperationException);
    }
  }

  public void testNextOnASingleElementIterator() throws Exception
  {
    d.addFirst("foo");
    Iterator<String> i = d.iterator();
    try {
      i.next();
      i.next();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

  public void testIteratorLoop()
  {
    Deque<Integer> di = new Deque<Integer>();
    for (int i = 1; i <= 5; i++) {
      di.addFirst(i);
    }
    int starter = 5;
    for (int t : di) {
      assertEquals(starter, t);
      starter--;
    }
  }

}
