package net.sf.anathema.demo.namegenerator.exalted;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.namegenerator.exalted.demo"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(NameGenerationDemo.class));
    //$JDemo-END$
    return suite;
  }
}