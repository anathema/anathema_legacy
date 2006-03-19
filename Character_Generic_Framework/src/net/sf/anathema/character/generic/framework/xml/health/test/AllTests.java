package net.sf.anathema.character.generic.framework.xml.health.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.framework.health.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(HealthTemplateParserTest.class);
    suite.addTestSuite(HealthTemplateTest.class);
    //$JUnit-END$
    return suite;
  }

}
