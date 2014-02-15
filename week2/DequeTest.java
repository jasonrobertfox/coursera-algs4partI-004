import java.util.NoSuchElementException;

import junit.framework.TestCase;

public class DequeTest extends TestCase
{

  public void testNewDequeIsEmpty() throws Exception
  {
    Deque<String> d = new Deque<String>();
    assertTrue(d.isEmpty());
    assertEquals(d.size(), 0);
  }

  public void testHandleExceptionForAddFirstNull() throws Exception
  {
    try {
      Deque<String> d = new Deque<String>();
      d.addFirst(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      assertTrue(e instanceof NullPointerException);
    }
  }

  public void testHandleExceptionForAddLastNull() throws Exception
  {
    try {
      Deque<String> d = new Deque<String>();
      d.addLast(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      assertTrue(e instanceof NullPointerException);
    }
  }

  public void testHandleExceptionForRemoveFirstFromEmpty() throws Exception
  {
    try {
      Deque<String> d = new Deque<String>();
      d.removeFirst();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

  public void testHandleExceptionForRemoveLastFromEmpty() throws Exception
  {
    try {
      Deque<String> d = new Deque<String>();
      d.removeLast();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

}
