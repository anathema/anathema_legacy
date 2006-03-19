package net.sf.anathema.character.impl.model.charm.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.concrete.charm.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(OxBodyTechniqueConfigurationTest.class);
    suite.addTestSuite(ComboTest.class);
    suite.addTestSuite(CharmTest.class);
    suite.addTestSuite(LearningCharmGroupTest.class);
    //$JUnit-END$
    return suite;
  }
}
