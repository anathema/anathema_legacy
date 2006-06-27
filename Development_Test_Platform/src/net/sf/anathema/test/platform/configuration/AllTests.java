package net.sf.anathema.test.platform.configuration;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.framework.configuration.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(AnathemaPreferencesTest.class);
    // $JUnit-END$
    return suite;
  }
}