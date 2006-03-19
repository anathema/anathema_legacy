package net.sf.anathema.character.generic.framework.magic.treelayout.util.test;

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