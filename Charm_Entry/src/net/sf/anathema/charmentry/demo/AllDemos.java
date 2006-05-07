package net.sf.anathema.charmentry.demo;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.development.character.charm.entry.demo"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(CharmEntryWizardDemo.class));
    //$JDemo-END$
    return suite;
  }
}