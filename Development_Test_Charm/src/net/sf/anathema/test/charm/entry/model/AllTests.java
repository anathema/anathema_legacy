package net.sf.anathema.test.charm.entry.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.development.character.charm.entry.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(ConfigurableCharmDataTest.class);
    // $JUnit-END$
    return suite;
  }

}
