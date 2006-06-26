package net.sf.anathema.test.campaign.concrete.plot;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.campaign.concrete.plot.test"); //$NON-NLS-1$
    //$JUnit-BEGIN$
    suite.addTestSuite(PlotElementContainerTest.class);
    //$JUnit-END$
    return suite;
  }
}
