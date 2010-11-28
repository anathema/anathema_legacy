package net.sf.anathema.test.character.generic.framework.charm;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.framework.magic.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(CharmNodeConnectorTest.class);
    suite.addTestSuite(CharmNodeBuilderTest.class);
    suite.addTestSuite(CharmGraphNodeBuilderTest.class);
    // $JUnit-END$
    return suite;
  }

}
