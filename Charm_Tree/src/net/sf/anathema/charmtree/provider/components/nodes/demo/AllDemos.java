package net.sf.anathema.charmtree.provider.components.nodes.demo;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.charmtree.provider.components.nodes.demo"); //$NON-NLS-1$
    // $JDemo-BEGIN$
    suite.addDemo(new DemoSuite(FlowTextDemo.class));
    // $JDemo-END$
    return suite;
  }

}
