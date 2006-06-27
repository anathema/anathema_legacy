package net.sf.anathema.test.lib.lang.clone;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.lib.lang.clone.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(DeepCloneCheckerTest.class);
    //JUnit-END$
    return suite;
  }
}