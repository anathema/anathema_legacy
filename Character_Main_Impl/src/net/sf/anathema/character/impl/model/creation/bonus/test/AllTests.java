package net.sf.anathema.character.impl.model.creation.bonus.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.concrete.creation.bonus.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(VirtueCostCalculatorTest.class);
    suite.addTestSuite(CharmCostCalculatorTest.class);
    suite.addTestSuite(AbilityCostCalculatorTest.class);
    suite.addTestSuite(AttributeCostCalculatorTest.class);
    //$JUnit-END$
    return suite;
  }
}