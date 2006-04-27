package net.sf.anathema.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

import net.sf.anathema.character.generic.framework.suite.AllCharacterGenericsFrameworkTests;

public class AllAnathemaTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema"); //$NON-NLS-1$
    suite.addTest(AllMainProjectTests.suite());
    suite.addTest(net.sf.anathema.campaign.suite.AllCampaignTests.suite());
    suite.addTest(net.sf.anathema.character.abyssal.suite.AllCharacterAbyssalTests.suite());
    suite.addTest(net.sf.anathema.character.impl.suite.AllCharacterImplTests.suite());
    suite.addTest(net.sf.anathema.character.generic.impl.suite.AllCharacterGenericImplTests.suite());
    suite.addTest(net.sf.anathema.character.library.suite.AllCharacterLibraryTests.suite());
    suite.addTest(net.sf.anathema.lib.suite.AllLibTests.suite());
    suite.addTest(AllCharacterGenericsFrameworkTests.suite());
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}