package net.sf.anathema.platform.svgtree.document.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BackwardsIterableTest {

  @Test
  public void testArrayIteration() throws Exception {
    Integer[] array = new Integer[]{1, 2, 3};
    for (int integer : new BackwardsIterable<Integer>(array)) {
      assertEquals(3, integer);
      return;
    }
  }

  @Test
  public void testListIteration() throws Exception {
    List<Integer> list = Arrays.asList(new Integer[]{1, 2, 3});
    for (int integer : new BackwardsIterable<Integer>(list)) {
      assertEquals(3, integer);
      return;
    }
  }
}