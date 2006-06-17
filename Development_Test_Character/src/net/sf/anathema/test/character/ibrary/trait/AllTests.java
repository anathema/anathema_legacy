package net.sf.anathema.test.character.ibrary.trait;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.test.character.ibrary.trait");
    //$JUnit-BEGIN$
    suite.addTestSuite(TraitFavorizationSetCasteTest.class);
    suite.addTestSuite(TraitFavorizationRequiredFavoredTest.class);
    suite.addTestSuite(TraitRulesTest.class);
    suite.addTestSuite(TraitFavorizationSetFavoredTest.class);
    //$JUnit-END$
    return suite;
  }

}
