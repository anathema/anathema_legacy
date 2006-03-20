package net.sf.anathema.character.impl.model.charm.combo.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.impl.model.charm.combo.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(ComboRulesTest.class);
    //$JUnit-END$
    return suite;
  }

}
