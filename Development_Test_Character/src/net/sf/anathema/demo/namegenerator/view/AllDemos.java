package net.sf.anathema.demo.namegenerator.view;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.namegenerator.view.demo"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(ExaltedNameGeneratorDemo.class));
    //$JDemo-END$
    return suite;
  }
}