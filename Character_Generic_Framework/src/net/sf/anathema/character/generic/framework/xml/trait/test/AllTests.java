package net.sf.anathema.character.generic.framework.xml.trait.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.xml.trait.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(GenericTraitTemplateParserTest.class);
    suite.addTestSuite(GenericTraitTemplateFactoryTest.class);
    suite.addTestSuite(GenericTraitTemplateFactoryParserTest.class);
    // $JUnit-END$
    return suite;
  }
}