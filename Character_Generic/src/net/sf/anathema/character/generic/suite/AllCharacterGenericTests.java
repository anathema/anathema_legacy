package net.sf.anathema.character.generic.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharacterGenericTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.character.generic.magic.charms.test.AllTests.suite());
    suite.addTest(net.sf.anathema.character.generic.traits.types.test.AllTests.suite());
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }

}
