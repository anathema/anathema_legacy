package net.sf.anathema.character.generic.framework.xml.rules.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.framework.xml.rules.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(AdditionalRulesTemplateParserTest.class);
    // $JUnit-END$
    return suite;
  }

}
