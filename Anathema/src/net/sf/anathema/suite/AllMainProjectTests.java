package net.sf.anathema.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllMainProjectTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.initialization.repository.test.AllTests.suite());
    // $JUnit-BEGIN$
    // $JUnit-END$
    return suite;
  }
}