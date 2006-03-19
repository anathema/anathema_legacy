package net.sf.anathema.character.abyssal.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharacterAbyssalTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.abyssal.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.character.abyssal.additional.test.AllTests.suite());
    //$JUnit-BEGIN$

    //$JUnit-END$
    return suite;
  }
}