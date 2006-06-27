package net.sf.anathema.test.lib.collection;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ListOrderedSetTest extends BasicTestCase {

  public void testAddAll() throws Exception {
    Set<Integer> testSet = new ListOrderedSet<Integer>();
    Set<Integer> insertSet = new HashSet<Integer>();
    Integer zero = new Integer(0);
    insertSet.add(zero);
    testSet.addAll(insertSet);
    assertTrue(testSet.contains(zero));
    assertEquals(1, testSet.size());
    testSet.addAll(insertSet);
    assertEquals(1, testSet.size());
  }
}