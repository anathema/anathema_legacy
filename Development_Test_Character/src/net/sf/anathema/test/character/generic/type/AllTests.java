package net.sf.anathema.test.character.generic.type;


import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.type.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(CharacterTypeTest.class);
    //$JUnit-END$
    return suite;
  }
}