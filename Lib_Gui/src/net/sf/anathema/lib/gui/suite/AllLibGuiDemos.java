package net.sf.anathema.lib.gui.suite;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllLibGuiDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.lib.gui.suite"); //$NON-NLS-1$
    suite.addDemo(net.sf.anathema.lib.gui.table.demo.AllDemos.suite());
    // $JDemo-BEGIN$

    // $JDemo-END$
    return suite;
  }

}
