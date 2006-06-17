package net.sf.anathema.test.character.abyssal.additional;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.abyssal.additional.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(LiegeBonusPointPoolTest.class);
    suite.addTestSuite(NecromancyLearnPoolTest.class);
    //$JUnit-END$
    return suite;
  }
}