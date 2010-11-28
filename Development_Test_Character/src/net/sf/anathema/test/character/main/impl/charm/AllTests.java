package net.sf.anathema.test.character.main.impl.charm;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.character.main.impl.charm"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(CharmTest.class);
    suite.addTestSuite(OxBodyTechniqueConfigurationTest.class);
    suite.addTestSuite(LearningCharmGroupTest.class);
    suite.addTestSuite(ComboTest.class);
    //$JUnit-END$
    return suite;
  }
}