package net.sf.anathema.campaign.music.impl.suite;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllMusicDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.campaign.music.impl.suite"); //$NON-NLS-1$
    suite.addDemo(net.sf.anathema.campaign.music.impl.view.demo.AllDemos.suite());
    //$JDemo-BEGIN$

    //$JDemo-END$
    return suite;
  }

}
