package net.sf.anathema.test.platform;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllFrameworkTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.framework.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.test.platform.configuration.AllTests.suite());
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}