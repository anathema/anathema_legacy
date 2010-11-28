package net.sf.anathema.test.character.generic.framework.xml;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.xml.essence.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(TemplateTypeParserTest.class);
    suite.addTestSuite(GenericTraitTemplateParserTest.class);
    suite.addTestSuite(GenericTraitTemplateFactoryTest.class);
    suite.addTestSuite(GenericTraitTemplateFactoryParserTest.class);
    suite.addTestSuite(CostParserTest.class);
    suite.addTestSuite(AlternateMinimumTraitTemplateParserTest.class);
    suite.addTestSuite(CharmPresentationPropertiesParserTest.class);
    suite.addTestSuite(EssenceTemplateParserTest.class);
    suite.addTestSuite(BasicTemplateParsingTestCase.class);
    suite.addTestSuite(GenericCreationPointsTest.class);
    suite.addTestSuite(PresentationPropertiesParserTest.class);
    suite.addTestSuite(GenericRestrictedTraitTemplateTest.class);
    suite.addTestSuite(BonusPointCostTemplateParserTest.class);
    suite.addTestSuite(GenericTraitTemplatePoolParserTest.class);
    suite.addTestSuite(AlternateMinimumRestrictionTest.class);
    suite.addTestSuite(HealthTemplateParserTest.class);
    suite.addTestSuite(GenericPresentationTemplateTest.class);
    suite.addTestSuite(HealthTemplateTest.class);
    suite.addTestSuite(AdditionalRulesTemplateParserTest.class);
    suite.addTestSuite(GenericBonusPointCostsTest.class);
    suite.addTestSuite(MagicTemplateParserTest.class);
    suite.addTestSuite(ExperienceTemplateParserTest.class);
    //$JUnit-END$
    return suite;
  }

}
