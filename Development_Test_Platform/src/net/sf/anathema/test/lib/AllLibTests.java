package net.sf.anathema.test.lib;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllLibTests {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.lib"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.test.lib.awt.AllTests.suite());
    suite.addTest(net.sf.anathema.test.lib.lang.AllTests.suite());
    suite.addTest(net.sf.anathema.test.lib.lang.clone.AllTests.suite());
    suite.addTest(net.sf.anathema.test.lib.AllTests.suite());
    suite.addTest(net.sf.anathema.test.lib.collection.AllTests.suite());
    // $JUnit-BEGIN$
    // $JUnit-END$
    return suite;
  }
}