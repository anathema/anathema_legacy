package net.sf.anathema.charmtree.presenter.view.test;

import net.sf.anathema.lib.testing.BasicTestCase;

public class RegularExpressionDotTest extends BasicTestCase {

  public void testRegularExpressionDotReplacement() throws Exception {
    String testString = "Anfang.Ende"; //$NON-NLS-1$
    String[] strings = testString.split("\\."); //$NON-NLS-1$
    assertEquals("Anfang", strings[0]); //$NON-NLS-1$
    assertEquals("Ende", strings[1]); //$NON-NLS-1$
  }
}