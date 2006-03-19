package net.sf.anathema.character.library.trait.favorable.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.library.trait.favorable.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(TraitFavorizationSetCasteTest.class);
    suite.addTestSuite(TraitFavorizationSetFavoredTest.class);
    suite.addTestSuite(TraitFavorizationRequiredFavoredTest.class);
    //$JUnit-END$
    return suite;
  }
}