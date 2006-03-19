package net.sf.anathema.character.impl.model.creation.bonus.basic.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.concrete.creation.bonus.basic.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(ElementCreationCostCalculatorTest.class);
    //$JUnit-END$
    return suite;
  }
}