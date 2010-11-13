package net.sf.anathema.demo.lib.workflow;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllLibWorkflowDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.lib.workflow.suite"); //$NON-NLS-1$
    suite.addDemo(net.sf.anathema.demo.lib.workflow.labelledvalue.view.AllDemos.suite());
    suite.addDemo(net.sf.anathema.demo.lib.workflow.textualdescription.view.AllDemos.suite());
    //$JDemo-BEGIN$

    //$JDemo-END$
    return suite;
  }

}
