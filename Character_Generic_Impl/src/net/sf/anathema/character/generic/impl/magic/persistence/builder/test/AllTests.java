package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.magic.persistence.builder.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(CostListBuilderTest.class);
    suite.addTestSuite(PrerequisiteListBuilderTest.class);
    suite.addTestSuite(DurationBuilderTest.class);
    suite.addTestSuite(GroupStringBuilderTest.class);
    suite.addTestSuite(CostBuilderTest.class);
    suite.addTestSuite(GenericTraitPrerequisiteBuilderTest.class);
    suite.addTestSuite(IdStringBuilderTest.class);
    suite.addTestSuite(CharmTypeBuilderTest.class);
    suite.addTestSuite(GenericIdStringBuilderTest.class);
    suite.addTestSuite(HealthCostBuilderTest.class);
    suite.addTestSuite(TraitPrerequisiteBuilderTest.class);
    //$JUnit-END$
    return suite;
  }

}
