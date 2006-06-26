package net.sf.anathema.test.campaign;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.sf.anathema.demo.campaign.AllCampaignDemos;
import de.jdemo.junit.Demo2TestConverter;

public class AllCampaignTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.anathema.campaign"); //$NON-NLS-1$
    suite.addTest(net.sf.anathema.test.campaign.concrete.plot.AllTests.suite());
    suite.addTest(Demo2TestConverter.createTest(AllCampaignDemos.suite()));
    // $JUnit-BEGIN$

    // $JUnit-END$
    return suite;
  }
}