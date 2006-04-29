package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.magic.persistence.builder.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(CostBuilderTest.class);
    suite.addTestSuite(GenericTraitPrerequisiteBuilderTest.class);
    suite.addTestSuite(CostListBuilderTest.class);
    suite.addTestSuite(HeaderStringBuilderTest.class);
    suite.addTestSuite(CharmTypeBuilderTest.class);
    suite.addTestSuite(HealthCostBuilderTest.class);
    suite.addTestSuite(TraitPrerequisiteBuilderTest.class);
    suite.addTestSuite(PrerequisiteListBuilderTest.class);
    suite.addTestSuite(DurationBuilderTest.class);
    //$JUnit-END$
    return suite;
  }

}
