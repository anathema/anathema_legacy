package net.sf.anathema.test.lib.lang;

import java.util.Arrays;

import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ArrayUtiltiesTest extends BasicTestCase {

  public void testMoveToGreaterIndex() throws Exception {
    String[] allStrings = new String[] { "1", "2", "3", "4" }; //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
    ArrayUtilities.moveObject(allStrings, 1, 3);
    assertTrue(Arrays.equals(new String[] { "1", "3", "4", "2" }, allStrings)); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
  }

  public void testMoveToLowerIndex() throws Exception {
    String[] allStrings = new String[] { "1", "2", "3", "4" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    ArrayUtilities.moveObject(allStrings, 2, 1);
    assertTrue(Arrays.equals(new String[] { "1", "3", "2", "4" }, allStrings)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

  public void testCreateIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(3);
    assertEquals(new Integer[] { 0, 1, 2, 3 }, integers);
  }

  public void testCreateNegativeIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-3);
    assertEquals(new Integer[] { -0, -1, -2, -3 }, integers);
  }

  public void testCreateLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(1, 5);
    assertEquals(new Integer[] { 1, 2, 3, 4, 5 }, integers);
  }

  public void testCreateNegativeLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-5, -1);
    assertEquals(new Integer[] { -5, -4, -3, -2, -1 }, integers);
  }

  public void testCreateCrossingLowBoundedIntegerArray() throws Exception {
    Integer[] integers = ArrayUtilities.createIntegerArray(-5, 1);
    assertEquals(new Integer[] { -5, -4, -3, -2, -1, 0, 1 }, integers);
  }
}