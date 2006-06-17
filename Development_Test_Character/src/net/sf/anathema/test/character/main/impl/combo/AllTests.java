package net.sf.anathema.test.character.main.impl.combo;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.character.main.impl.combo"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(ReflexiveCharmComboRulesTest.class);
    suite.addTestSuite(ComboRulesTest.class);
    suite.addTestSuite(SupplementalCharmComboRulesTest.class);
    suite.addTestSuite(ExtraActionCharmComboRulesTest.class);
    suite.addTestSuite(SimpleCharmComboRulesTest.class);
    //$JUnit-END$
    return suite;
  }
}