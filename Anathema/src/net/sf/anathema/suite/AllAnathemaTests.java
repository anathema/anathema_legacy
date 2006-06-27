package net.sf.anathema.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllAnathemaTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.lib.suite.AllLibTests.suite());
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}