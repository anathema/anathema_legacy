package net.sf.anathema.character.impl.model.advance.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.concrete.advance.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(CostAnalyzerTest.class);
    suite.addTestSuite(ExperiencePointCalculatorTest.class);
    suite.addTestSuite(RatingCostsTest.class);
    //$JUnit-END$
    return suite;
  }

}
