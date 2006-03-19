package net.sf.anathema.character.library.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharacterLibraryTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.library.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.character.library.trait.rules.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.library.trait.favorable.test.AllTests.suite());
    //$JUnit-BEGIN$

    //$JUnit-END$
    return suite;
  }
}