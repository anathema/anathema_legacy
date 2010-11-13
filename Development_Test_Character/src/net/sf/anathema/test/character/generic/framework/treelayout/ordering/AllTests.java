package net.sf.anathema.test.character.generic.framework.treelayout.ordering;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Test for net.sf.anathema.character.generic.framework.magic.positionhints.sugiyama.ordering.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(VertexOrdererTest.class);
    suite.addTestSuite(ProperHierarchicalGraphTest.class);
    suite.addTestSuite(HierarchyBuilderTest.class);
    // $JUnit-END$
    return suite;
  }
}