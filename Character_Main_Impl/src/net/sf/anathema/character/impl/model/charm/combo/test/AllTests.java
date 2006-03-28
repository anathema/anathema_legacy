package net.sf.anathema.character.impl.model.charm.combo.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.impl.model.charm.combo.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(SupplementalCharmComboRulesTest.class);
    suite.addTestSuite(ComboRulesTest.class);
    suite.addTestSuite(ExtraActionCharmComboRulesTest.class);
    suite.addTestSuite(SimpleCharmComboRulesTest.class);
    suite.addTestSuite(ReflexiveCharmComboRulesTest.class);
    //$JUnit-END$
    return suite;
  }

}
