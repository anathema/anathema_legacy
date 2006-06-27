package net.sf.anathema.demo.campaign.music.view;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.campaign.music.impl.view.demo"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(TrackDetailViewDemo.class));
    //$JDemo-END$
    return suite;
  }
}