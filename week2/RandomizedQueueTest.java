import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

public class RandomizedQueueTest extends TestCase
{
  private RandomizedQueue<String> rq;

  @Override
  protected void setUp() throws Exception
  {
    rq = new RandomizedQueue<String>();
  }

  public void testIsEmptyByDefault() throws Exception
  {
    assertTrue(rq.isEmpty());
    assertEquals(0, rq.size());
  }

  public void testHandleExceptinOnAddNull()
  {
    try {
      rq.enqueue(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      assertTrue(e instanceof NullPointerException);
    }
  }

  public void testEnqueueSingleItem()
  {
    rq.enqueue("test");
    assertEquals(1, rq.size());
    assertFalse(rq.isEmpty());
  }

  public void testSampleSingleItem()
  {
    rq.enqueue("test");
    assertEquals("test", rq.sample());
    assertEquals(1, rq.size());
  }

  public void testDequeueSingleItem()
  {
    rq.enqueue("test");
    assertEquals("test", rq.dequeue());
    assertEquals(0, rq.size());
  }

  public void testHandleExceptionForDequeueFromEmpty() throws Exception
  {
    try {
      rq.dequeue();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

  public void testHandleExceptionForSampleFromEmpty() throws Exception
  {
    try {
      rq.sample();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

  public void testEnqueueMany()
  {
    int examples = 20;
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    for (int i = 1; i <= examples; i++) {
      q.enqueue(i);
    }
    assertEquals(examples, q.size());
    int[] results = new int[examples];
    for (int i = 0; i < examples; i++) {
      results[i] = q.dequeue();
    }
    Arrays.sort(results);
    for (int i = 0; i < examples; i++) {
      assertEquals(i + 1, results[i]);
    }
  }

  public void testRandomSample()
  {
    int examples = 10;
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    for (int i = 1; i <= examples; i++) {
      q.enqueue(i);
    }

    double sampleCount = 0;
    for (double i = 0; i < 10000; i++) {
      if (q.sample() == 1) {
        sampleCount++;
      }
    }
    assertEquals(0.1, sampleCount / (10000), 0.01);
  }

  public void testRandomDequeue()
  {
    double samples = 5000;
    double[] results = new double[3];
    results[0] = 0;
    results[2] = 0;
    results[2] = 0;
    for (int i = 0; i < samples; i++) {
      RandomizedQueue<String> q = new RandomizedQueue<String>();
      q.enqueue("a");
      q.enqueue("b");
      q.enqueue("c");
      int c = 0;
      String out;
      do {
        c++;
        out = q.dequeue();
      } while (!out.equals("b"));
      results[c - 1] += 1;
    }

    assertEquals(0.333, results[0] / samples, 0.05);
    assertEquals(0.333, results[1] / samples, 0.05);
    assertEquals(0.333, results[2] / samples, 0.05);

  }

  public void testEmptyOscillation()
  {
    rq.enqueue("a");
    rq.enqueue("b");
    rq.dequeue();
    rq.dequeue();
    rq.enqueue("c");
    assertEquals("c", rq.dequeue());
  }

  public void testExceptionOnRemoveMethod() throws Exception
  {
    rq.enqueue("foo");
    Iterator<String> i = rq.iterator();
    try {
      i.remove();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      assertTrue(e instanceof UnsupportedOperationException);
    }
  }

  public void testNextOnASingleElementIterator() throws Exception
  {
    rq.enqueue("foo");
    Iterator<String> i = rq.iterator();
    try {
      i.next();
      i.next();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      assertTrue(e instanceof NoSuchElementException);
    }
  }

  public void testIterationOnRandomizedQueue()
  {
    int examples = 10;
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    for (int i = 1; i <= examples; i++) {
      q.enqueue(i);
    }
    assertEquals(examples, q.size());

    double sampleCount = 0;

    for (double i = 0; i < 10000; i++) {
      for (int s : q) {
        if (s == 9) {
          sampleCount++;
        }
        break;
      }
    }
    assertEquals(0.1, sampleCount / (10000), 0.01);
  }

  public void testSumOfIteration()
  {
    int examples = 10;
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    for (int i = 1; i <= examples; i++) {
      q.enqueue(i);
    }
    int sum = 0;
    Iterator<Integer> l = q.iterator();
    for (int s : q) {
      sum += s;
    }

    assertEquals(55, sum);
  }

  public void testEnqueueDequeue()
  {
    try {
      rq.enqueue("a");
      rq.dequeue();
      rq.dequeue();
    } catch (NoSuchElementException e) {
      rq.enqueue("c");
      rq.dequeue();
      rq.enqueue("b");
      assertEquals("b", rq.dequeue());
    }
  }
}
