package net.sf.anathema.demo.lib.gui.table;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.lib.gui.table.demo"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(SmartTableDemo.class));
    //$JDemo-END$
    return suite;
  }

}
