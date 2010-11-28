package net.sf.anathema.test.character.generic.traits.types;


import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.traits.types.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(ValuedTraitTypeTest.class);
    //$JUnit-END$
    return suite;
  }

}
