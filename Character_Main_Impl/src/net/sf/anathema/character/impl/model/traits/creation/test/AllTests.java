package net.sf.anathema.character.impl.model.traits.creation.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.concrete.traits.factory.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(AbilityTraitTypeGroupFactoryTest.class);
    //$JUnit-END$
    return suite;
  }
}