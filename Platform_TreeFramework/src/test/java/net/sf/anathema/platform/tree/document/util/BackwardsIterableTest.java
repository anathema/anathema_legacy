package net.sf.anathema.platform.tree.document.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BackwardsIterableTest {

  @Test
  public void testArrayIteration() throws Exception {
    Integer[] array = new Integer[]{1, 2, 3};
    int integer1 = Iterables.get(new BackwardsIterable<>(array), 0);
    assertEquals(3, integer1);
  }

  @Test
  public void testListIteration() throws Exception {
    List<Integer> list = Lists.newArrayList(1, 2, 3);
    int integer1 = Iterables.get(new BackwardsIterable<>(list), 0);
    assertEquals(3, integer1);
  }
}