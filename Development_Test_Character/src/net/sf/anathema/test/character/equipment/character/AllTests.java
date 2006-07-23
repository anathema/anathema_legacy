package net.sf.anathema.test.character.equipment.character;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.character.equipment.character"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(EquipmentStringBuilderTest.class);
    suite.addTestSuite(EquipmentItemPresenterTest.class);
    suite.addTestSuite(EquipmentAdditionalPresenterTest.class);
    //$JUnit-END$
    return suite;
  }
}