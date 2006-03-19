package net.sf.anathema.lib.lang.test;

import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public class StringUtilitiesTest extends BasicTestCase {

  public void testFindLineBreaks() throws Exception {
    assertEquals(new int[] { 6 }, AnathemaStringUtilities.findBreakPoints("Hallo Du!", 2)); //$NON-NLS-1$
    assertEquals(new int[] { 6, 9 }, AnathemaStringUtilities.findBreakPoints("Hallo Du da!", 3)); //$NON-NLS-1$
    assertEquals(new int[] { 6 }, AnathemaStringUtilities.findBreakPoints("Hallo-Du!", 2)); //$NON-NLS-1$
    assertEquals(new int[] { 6, 9 }, AnathemaStringUtilities.findBreakPoints("Hallo-Du da!", 3)); //$NON-NLS-1$
    assertEquals(new int[] { 6, 13 }, AnathemaStringUtilities.findBreakPoints("Oh Du Lebest drauﬂen!", 3)); //$NON-NLS-1$
    assertEquals(new int[] { 6, 12 }, AnathemaStringUtilities.findBreakPoints("Hallo Du da drauﬂen!", 3)); //$NON-NLS-1$
    assertEquals(new int[] { 0, 3 }, AnathemaStringUtilities.findBreakPoints("Zu kurz.", 3)); //$NON-NLS-1$
  }

  public void testCutOffLastCharacters() throws Exception {
    assertEquals("S", AnathemaStringUtilities.cutOffLastCharacters("Su", 1)); //$NON-NLS-1$ //$NON-NLS-2$
  }
}