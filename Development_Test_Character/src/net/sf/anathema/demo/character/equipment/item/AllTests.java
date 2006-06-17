package net.sf.anathema.demo.character.equipment.item;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.character.equipment.item"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(EquipmentItemCreationTest.class);
    //$JUnit-END$
    return suite;
  }
}