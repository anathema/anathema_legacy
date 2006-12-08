package net.sf.anathema.test.lib.collection;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.lib.collection.ListOrderedSet;

import org.junit.Assert;
import org.junit.Test;

public class ListOrderedSetTest {

  @Test
  public void testAddAll() throws Exception {
    Set<Integer> testSet = new ListOrderedSet<Integer>();
    Set<Integer> insertSet = new HashSet<Integer>();
    Integer zero = new Integer(0);
    insertSet.add(zero);
    testSet.addAll(insertSet);
    Assert.assertTrue(testSet.contains(zero));
    Assert.assertEquals(1, testSet.size());
    testSet.addAll(insertSet);
    Assert.assertEquals(1, testSet.size());
  }
}