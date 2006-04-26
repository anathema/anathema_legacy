package net.sf.anathema.test.platform.itemdata;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.platform.itemdata"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(BasicItemDataTest.class);
    //$JUnit-END$
    return suite;
  }
}