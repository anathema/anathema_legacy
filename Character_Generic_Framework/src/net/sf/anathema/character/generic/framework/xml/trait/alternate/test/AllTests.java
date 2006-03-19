package net.sf.anathema.character.generic.framework.xml.trait.alternate.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.framework.xml.trait.alternate.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(GenericRestrictedTraitTemplateTest.class);
    suite.addTestSuite(AlternateMinimumTraitTemplateParserTest.class);
    suite.addTestSuite(AlternateMinimumRestrictionTest.class);
    //$JUnit-END$
    return suite;
  }

}
