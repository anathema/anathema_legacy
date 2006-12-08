package net.sf.anathema.test.lib.lang;

import java.util.Arrays;

import net.sf.anathema.lib.lang.IntegerUtilities;

import org.junit.Assert;
import org.junit.Test;

public class IntegerUtilitiesTest {

  @Test
  public void testPermutateArray() throws Exception {
    Assert.assertTrue(Arrays.equals(new int[] { 2, 1 }, IntegerUtilities.permutate(new int[] { 1, 2 })));
    Assert.assertTrue(Arrays.equals(new int[] { 2, 4, 1, 3 }, IntegerUtilities.permutate(new int[] { 1, 2, 3, 4 })));
    Assert.assertTrue(Arrays.equals(new int[] { 3, 6, 2, 5, 1, 4 }, IntegerUtilities.permutate(new int[] {
        1,
        2,
        3,
        4,
        5,
        6 })));
  }

  @Test
  public void testIsNullOrZero() throws Exception {
    Assert.assertTrue(IntegerUtilities.isNullOrZero(new Integer(0)));
    Assert.assertTrue(IntegerUtilities.isNullOrZero(null));
    Assert.assertFalse(IntegerUtilities.isNullOrZero(new Integer(2)));
  }
}