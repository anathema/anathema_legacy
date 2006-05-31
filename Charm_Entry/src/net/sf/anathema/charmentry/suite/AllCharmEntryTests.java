package net.sf.anathema.charmentry.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharmEntryTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.charmentry.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.charmentry.model.test.AllTests.suite());
    //$JUnit-BEGIN$

    //$JUnit-END$
    return suite;
  }

}
