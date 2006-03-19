package net.sf.anathema.lib.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllLibTests {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.lib"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.lib.awt.test.AllTests.suite());
    suite.addTest(net.sf.anathema.lib.lang.test.AllTests.suite());
    suite.addTest(net.sf.anathema.lib.lang.clone.test.AllTests.suite());
    suite.addTest(net.sf.anathema.lib.test.AllTests.suite());
    suite.addTest(net.sf.anathema.lib.collection.test.AllTests.suite());
    // $JUnit-BEGIN$
    // $JUnit-END$
    return suite;
  }
}