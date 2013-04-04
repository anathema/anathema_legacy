package net.sf.anathema.lib.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class StringUtilitiesTest {
  @Test
  public void testFindLineBreaks() throws Exception {
    Assert.assertTrue(Arrays.equals(new int[] { 6 }, StringUtilities.findBreakPoints("Hallo Du!", 2)));
    Assert.assertTrue(Arrays.equals(new int[] { 6, 9 }, StringUtilities.findBreakPoints("Hallo Du da!", 3)));
    Assert.assertTrue(Arrays.equals(new int[] { 6 }, StringUtilities.findBreakPoints("Hallo-Du!", 2)));
    Assert.assertTrue(Arrays.equals(new int[] { 6, 9 }, StringUtilities.findBreakPoints("Hallo-Du da!", 3)));
    Assert.assertTrue(Arrays.equals(new int[] { 6, 13 }, StringUtilities.findBreakPoints("Oh Du Lebest draussen!", 3)));
    int[] breakPoints = StringUtilities.findBreakPoints("Hallo Du da drueben!", 3);
    Assert.assertTrue(Arrays.equals(new int[] { 6, 12 }, breakPoints));
    Assert.assertTrue(Arrays.equals(new int[] { 0, 3 }, StringUtilities.findBreakPoints("Zu kurz.", 3)));
  }

  @Test
  public void testCutOffLastCharacters() throws Exception {
    Assert.assertEquals("S", StringUtilities.cutOffLastCharacters("Su", 1));
  }
}