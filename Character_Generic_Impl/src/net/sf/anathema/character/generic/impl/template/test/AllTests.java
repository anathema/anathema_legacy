package net.sf.anathema.character.generic.impl.template.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.template.test");
    //$JUnit-BEGIN$
    suite.addTestSuite(TemplateRegistryTest.class);
    //$JUnit-END$
    return suite;
  }

}
