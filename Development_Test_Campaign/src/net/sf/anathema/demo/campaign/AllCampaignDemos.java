package net.sf.anathema.demo.campaign;

import net.sf.anathema.demo.campaign.view.plot.PlotViewDemo;
import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllCampaignDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.demo.campaign.presenter"); //$NON-NLS-1$
    // $JDemo-BEGIN$
    suite.addDemo(new DemoSuite(PlotViewDemo.class));
    // $JDemo-END$
    return suite;
  }

}
