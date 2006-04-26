package net.sf.anathema.test.platform;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllPlatformTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.abyssal.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.test.platform.itemdata.AllTests.suite());
    //$JUnit-BEGIN$

    //$JUnit-END$
    return suite;
  }
}