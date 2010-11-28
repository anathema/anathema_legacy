package net.sf.anathema.test.character.main.impl.costs;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.character.main.impl"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(CostAnalyzerTest.class);
    suite.addTestSuite(ExperiencePointCalculatorTest.class);
    suite.addTestSuite(CharmCostCalculatorTest.class);
    suite.addTestSuite(RatingCostsTest.class);
    suite.addTestSuite(AbilityCostCalculatorTest.class);
    suite.addTestSuite(VirtueCostCalculatorTest.class);
    suite.addTestSuite(ElementCreationCostCalculatorTest.class);
    suite.addTestSuite(AttributeCostCalculatorTest.class);
    //$JUnit-END$
    return suite;
  }
}