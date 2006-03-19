package net.sf.anathema.character.generic.framework.xml.experience.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.xml.experience.test"); //$NON-NLS-1$
    // $JUnit-BEGIN$
    suite.addTestSuite(ExperienceTemplateParserTest.class);
    // $JUnit-END$
    return suite;
  }

}
