package net.sf.anathema.test.lib.lang;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.lib.lang.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(IntegerUtilitiesTest.class);
    suite.addTestSuite(ArrayUtiltiesTest.class);
    suite.addTestSuite(StringUtilitiesTest.class);
    //$JUnit-END$
    return suite;
  }
}