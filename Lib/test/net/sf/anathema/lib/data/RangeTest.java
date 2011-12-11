package net.sf.anathema.lib.data;

import org.junit.Assert;
import org.junit.Test;

public class RangeTest {

  @Test
  public void testBasics() {
    Range range = new Range(2, 7);
    Assert.assertEquals("lowerBound is 2", range.getLowerBound(), 2); //$NON-NLS-1$
    Assert.assertEquals("upperBound is 7", range.getUpperBound(), 7); //$NON-NLS-1$
    Assert.assertEquals("width is 6", range.getWidth(), 6); //$NON-NLS-1$
  }

  @Test
  public void testContainsInteger() {
    Range range = new Range(2, 7);
    Assert.assertTrue("2 is within", range.contains(2)); //$NON-NLS-1$
    Assert.assertTrue("7 is within", range.contains(7)); //$NON-NLS-1$
    Assert.assertTrue("1 is not within", !range.contains(1)); //$NON-NLS-1$
    Assert.assertTrue("8 is not within", !range.contains(8)); //$NON-NLS-1$
  }

  @Test
  public void testContainsRange() {
    Range range = new Range(2, 7);
    Range inner = new Range(3, 4);
    Range outer = new Range(1, 4);
    Assert.assertTrue("Inner-Range: ", range.contains(inner)); //$NON-NLS-1$
    Assert.assertTrue("Outer-Range: ", !range.contains(outer)); //$NON-NLS-1$
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(new Range(1, 1), new Range(1, 1));
  }

  @Test
  public void testEqualsWithNonRange() {
    Assert.assertTrue(!(new Range(1, 1).equals(new Object())));
  }

  @Test
  public void testOtherEquals() {
    Assert.assertEquals(new Range(2, 5), new Range(2, 5));
  }

  @Test
  public void testOtherToString() {
    Assert.assertEquals("[2,3]", new Range(2, 3).toString()); //$NON-NLS-1$
  }

  @Test
  public void testToString() {
    Assert.assertEquals("[1,2]", new Range(1, 2).toString()); //$NON-NLS-1$
  }
}