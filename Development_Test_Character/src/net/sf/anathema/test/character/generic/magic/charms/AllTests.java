package net.sf.anathema.test.character.generic.magic.charms;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.magic.charms.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(DurationTest.class);
    //$JUnit-END$
    return suite;
  }

}
