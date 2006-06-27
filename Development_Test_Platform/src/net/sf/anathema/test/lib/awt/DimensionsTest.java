package net.sf.anathema.test.lib.awt;

import java.awt.Dimension;

import junit.framework.TestCase;
import net.sf.anathema.lib.awt.Dimensions;

public class DimensionsTest extends TestCase {

  public DimensionsTest(String title) {
    super(title);
  }

  public void testMinimize() {
    Dimension widthSmaller = new Dimension(2, 6);
    Dimension heightSmaller = new Dimension(5, 3);
    Dimension expected = new Dimension(widthSmaller.width, heightSmaller.height);
    assertEquals(expected, Dimensions.minimize(widthSmaller, heightSmaller));
  }

  public void testMaximize() {
    Dimension widthLarger = new Dimension(2, 1);
    Dimension heightLarger = new Dimension(1, 3);
    Dimension expected = new Dimension(widthLarger.width, heightLarger.height);
    assertEquals(expected, Dimensions.maximize(widthLarger, heightLarger));
  }
}