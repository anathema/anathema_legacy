package net.sf.anathema.test.character.equipment.impl.creation.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.character.equipment.impl.creation.model"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(WeaponDamageModelTest.class);
    //$JUnit-END$
    return suite;
  }
}