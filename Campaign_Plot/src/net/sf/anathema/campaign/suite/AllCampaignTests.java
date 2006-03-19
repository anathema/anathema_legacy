package net.sf.anathema.campaign.suite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCampaignTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.campaign"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.campaign.concrete.plot.test.AllTests.suite());
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}