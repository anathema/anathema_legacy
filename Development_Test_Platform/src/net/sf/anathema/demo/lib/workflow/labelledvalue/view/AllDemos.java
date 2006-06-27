package net.sf.anathema.demo.lib.workflow.labelledvalue.view;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.lib.workflow.labelledvalue.view.demo"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(LabelledStringValueViewDemo.class));
    //$JDemo-END$
    return suite;
  }

}
