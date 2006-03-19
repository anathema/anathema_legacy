package net.sf.anathema.character.generic.framework.xml.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.xml.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(TemplateTypeParserTest.class);
    // $JUnit-END$
    return suite;
  }
}