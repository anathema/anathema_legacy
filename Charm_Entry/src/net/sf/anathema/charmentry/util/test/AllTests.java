package net.sf.anathema.charmentry.util.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.development.character.charm.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(CharmUtilitiesTest.class);
    //$JUnit-END$
    return suite;
  }
}