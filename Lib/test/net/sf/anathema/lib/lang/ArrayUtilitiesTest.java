package net.sf.anathema.lib.lang;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilitiesTest {

  @Test
  public void testCreateIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(3);
    Assert.assertArrayEquals(new Integer[] { 0, 1, 2, 3 }, integers);
  }

  @Test
  public void testCreateNegativeIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-3);
    Assert.assertArrayEquals(new Integer[] { -0, -1, -2, -3 }, integers);
  }

  @Test
  public void testCreateLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(1, 5);
    Assert.assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, integers);
  }

  @Test
  public void testCreateNegativeLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-5, -1);
    Assert.assertArrayEquals(new Integer[] { -5, -4, -3, -2, -1 }, integers);
  }

  @Test
  public void testCreateCrossingLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-5, 1);
    Assert.assertArrayEquals(new Integer[] { -5, -4, -3, -2, -1, 0, 1 }, integers);
  }
}