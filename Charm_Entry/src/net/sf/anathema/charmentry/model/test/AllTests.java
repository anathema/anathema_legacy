package net.sf.anathema.charmentry.model.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.development.character.charm.entry.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(PrimaryPrerequisiteLegalityCheckerTest.class);
    suite.addTestSuite(ConfigurableCharmDataTest.class);
    suite.addTestSuite(CharmEntryModelTest.class);
    //$JUnit-END$
    return suite;
  }

}
