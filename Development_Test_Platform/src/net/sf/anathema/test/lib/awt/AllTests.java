package net.sf.anathema.test.lib.awt;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.lib.awt.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(ColorUtilitiesTest.class);
    suite.addTestSuite(DimensionsTest.class);
    suite.addTestSuite(PointsTest.class);
    //$JUnit-END$
    return suite;
  }
}