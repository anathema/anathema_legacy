package net.sf.anathema.character.generic.impl.magic.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.character.generic.impl.magic.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(MartialArtsUtilitiesTest.class);
    suite.addTestSuite(CharmTest.class);
    suite.addTestSuite(CharmUtilitiesTest.class);
    //$JUnit-END$
    return suite;
  }

}
