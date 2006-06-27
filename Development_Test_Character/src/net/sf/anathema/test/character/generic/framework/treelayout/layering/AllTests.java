package net.sf.anathema.test.character.generic.framework.treelayout.layering;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Test for net.sf.anathema.character.generic.framework.magic.positionhints.sugiyama.layering.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(LongestPathLayererTest.class);
    // $JUnit-END$
    return suite;
  }

}
