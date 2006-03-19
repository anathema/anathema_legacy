package net.sf.anathema.character.generic.framework.xml.presentation.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.xml.presentation.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(GenericPresentationTemplateTest.class);
    suite.addTestSuite(CharmPresentationPropertiesParserTest.class);
    suite.addTestSuite(PresentationPropertiesParserTest.class);
    //$JUnit-END$
    return suite;
  }
}