package net.sf.anathema.lib.lang.test;

import net.sf.anathema.lib.lang.IntegerUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public class IntegerUtilitiesTest extends BasicTestCase {

  public void testPermutateArray() throws Exception {
    assertEquals(new int[] { 2, 1 }, IntegerUtilities.permutate(new int[] { 1, 2 }));
    assertEquals(new int[] { 2, 4, 1, 3 }, IntegerUtilities.permutate(new int[] { 1, 2, 3, 4 }));
    assertEquals(new int[] { 3, 6, 2, 5, 1, 4 }, IntegerUtilities.permutate(new int[] { 1, 2, 3, 4, 5, 6 }));
  }

  public void testIsNullOrZero() throws Exception {
    assertTrue(IntegerUtilities.isNullOrZero(new Integer(0)));
    assertTrue(IntegerUtilities.isNullOrZero(null));
    assertFalse(IntegerUtilities.isNullOrZero(new Integer(2)));
  }
}