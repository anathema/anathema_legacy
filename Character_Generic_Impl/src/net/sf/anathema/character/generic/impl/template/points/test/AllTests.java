package net.sf.anathema.character.generic.impl.template.points.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.template.points.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(DefaultBonusPointsTest.class);
    // $JUnit-END$
    return suite;
  }

}
