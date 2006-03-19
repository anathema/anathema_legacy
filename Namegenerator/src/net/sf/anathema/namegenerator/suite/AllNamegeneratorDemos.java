package net.sf.anathema.namegenerator.suite;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllNamegeneratorDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.namegenerator.exalted.demo"); //$NON-NLS-1$
    suite.addDemo(net.sf.anathema.namegenerator.view.demo.AllDemos.suite());
    suite.addDemo(net.sf.anathema.namegenerator.exalted.demo.AllDemos.suite());
    //$JDemo-BEGIN$
    //$JDemo-END$
    return suite;
  }
}