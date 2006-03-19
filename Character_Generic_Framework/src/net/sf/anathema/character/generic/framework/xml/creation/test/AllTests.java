package net.sf.anathema.character.generic.framework.xml.creation.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.xml.creation.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(BonusPointCostTemplateParserTest.class);
    suite.addTestSuite(GenericCreationPointsTest.class);
    suite.addTestSuite(GenericBonusPointCostsTest.class);
    // $JUnit-END$
    return suite;
  }

}