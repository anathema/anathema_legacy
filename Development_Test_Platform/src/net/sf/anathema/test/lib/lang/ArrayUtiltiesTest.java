package net.sf.anathema.test.lib.lang;

import java.util.Arrays;

import net.sf.anathema.lib.lang.ArrayUtilities;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtiltiesTest {

  @Test
  public void testMoveToGreaterIndex() throws Exception {
    String[] allStrings = new String[] { "1", "2", "3", "4" }; //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
    ArrayUtilities.moveObject(allStrings, 1, 3);
    Assert.assertTrue(Arrays.equals(new String[] { "1", "3", "4", "2" }, allStrings)); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
  }

  @Test
  public void testMoveToLowerIndex() throws Exception {
    String[] allStrings = new String[] { "1", "2", "3", "4" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    ArrayUtilities.moveObject(allStrings, 2, 1);
    Assert.assertTrue(Arrays.equals(new String[] { "1", "3", "2", "4" }, allStrings)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

  @Test
  public void testCreateIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(3);
    Assert.assertEquals(new Integer[] { 0, 1, 2, 3 }, integers);
  }

  @Test
  public void testCreateNegativeIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-3);
    Assert.assertEquals(new Integer[] { -0, -1, -2, -3 }, integers);
  }

  @Test
  public void testCreateLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(1, 5);
    Assert.assertEquals(new Integer[] { 1, 2, 3, 4, 5 }, integers);
  }

  @Test
  public void testCreateNegativeLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-5, -1);
    Assert.assertEquals(new Integer[] { -5, -4, -3, -2, -1 }, integers);
  }

  @Test
  public void testCreateCrossingLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-5, 1);
    Assert.assertEquals(new Integer[] { -5, -4, -3, -2, -1, 0, 1 }, integers);
  }
}