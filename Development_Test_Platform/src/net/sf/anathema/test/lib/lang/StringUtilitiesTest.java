package net.sf.anathema.test.lib.lang;

import java.util.Arrays;

import net.sf.anathema.lib.lang.AnathemaStringUtilities;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilitiesTest {
  @Test
  public void testFindLineBreaks() throws Exception {
    Assert.assertTrue(Arrays.equals(new int[] { 6 }, AnathemaStringUtilities.findBreakPoints("Hallo Du!", 2))); //$NON-NLS-1$
    Assert.assertTrue(Arrays.equals(new int[] { 6, 9 }, AnathemaStringUtilities.findBreakPoints("Hallo Du da!", 3))); //$NON-NLS-1$
    Assert.assertTrue(Arrays.equals(new int[] { 6 }, AnathemaStringUtilities.findBreakPoints("Hallo-Du!", 2))); //$NON-NLS-1$
    Assert.assertTrue(Arrays.equals(new int[] { 6, 9 }, AnathemaStringUtilities.findBreakPoints("Hallo-Du da!", 3))); //$NON-NLS-1$
    Assert.assertTrue(Arrays.equals(new int[] { 6, 13 }, AnathemaStringUtilities.findBreakPoints(
        "Oh Du Lebest drauﬂen!", 3))); //$NON-NLS-1$
    Assert.assertTrue(Arrays.equals(new int[] { 6, 12 }, AnathemaStringUtilities.findBreakPoints(
        "Hallo Du da drauﬂen!", 3))); //$NON-NLS-1$
    Assert.assertTrue(Arrays.equals(new int[] { 0, 3 }, AnathemaStringUtilities.findBreakPoints("Zu kurz.", 3))); //$NON-NLS-1$
  }

  @Test
  public void testCutOffLastCharacters() throws Exception {
    Assert.assertEquals("S", AnathemaStringUtilities.cutOffLastCharacters("Su", 1)); //$NON-NLS-1$ //$NON-NLS-2$
  }
}