package net.sf.anathema.character.impl.model.traits.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.concrete.traits.abilities.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(FavorableTraitTest.class);
    //$JUnit-END$
    return suite;
  }

}
