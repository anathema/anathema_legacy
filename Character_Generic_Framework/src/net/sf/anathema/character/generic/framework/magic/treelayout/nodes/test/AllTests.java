package net.sf.anathema.character.generic.framework.magic.treelayout.nodes.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.charmtree.support.sugiyama.nodes.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(WeightedNodeComparatorTest.class);
    // $JUnit-END$
    return suite;
  }

}
