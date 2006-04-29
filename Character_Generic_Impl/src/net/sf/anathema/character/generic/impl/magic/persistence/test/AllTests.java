package net.sf.anathema.character.generic.impl.magic.persistence.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.concrete.charm.persistence.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(CharmBuilderTest.class);
    suite.addTestSuite(CharmSetBuilderTest.class);
    suite.addTestSuite(GenericCharmBuilderTest.class);
    // $JUnit-END$
    return suite;
  }
}
