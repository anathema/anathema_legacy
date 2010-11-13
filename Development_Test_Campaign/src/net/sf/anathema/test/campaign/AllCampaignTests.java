package net.sf.anathema.test.campaign;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
    net.sf.anathema.test.campaign.concrete.plot.AllTests.class,
    net.sf.anathema.test.campaign.dirty.AllTests.class })
public class AllCampaignTests {
  // nothing to do
}