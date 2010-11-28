package net.sf.anathema.test.character.generic.framework.treelayout.util;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.charmtree.support.sugiyama.util.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(IncidentMatrixBuilderTest.class);
    // $JUnit-END$
    return suite;
  }
}