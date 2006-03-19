package net.sf.anathema.lib.workflow.suite;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllLibWorkflowDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.lib.workflow.suite"); //$NON-NLS-1$
    suite.addDemo(net.sf.anathema.lib.workflow.labelledvalue.view.demo.AllDemos.suite());
    suite.addDemo(net.sf.anathema.lib.workflow.textualdescription.view.demo.AllDemos.suite());
    //$JDemo-BEGIN$

    //$JDemo-END$
    return suite;
  }

}
