package net.sf.anathema.test.character;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCharacterTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.abyssal.suite"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.test.character.equipment.item.AllTests.suite());
    //$JUnit-BEGIN$

    //$JUnit-END$
    return suite;
  }
}