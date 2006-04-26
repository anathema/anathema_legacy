package net.sf.anathema.test.character;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllCharacterDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.test.character"); //$NON-NLS-1$
    suite.addDemo(net.sf.anathema.test.character.equipment.item.AllDemos.suite());
        //$JDemo-BEGIN$

    //$JDemo-END$
    return suite;
  }
}