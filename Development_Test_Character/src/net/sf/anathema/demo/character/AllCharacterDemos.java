package net.sf.anathema.demo.character;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllCharacterDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.test.character"); //$NON-NLS-1$
    suite.addDemo(net.sf.anathema.demo.character.craft.AllDemos.suite());
    suite.addDemo(net.sf.anathema.demo.character.equipment.character.AllDemos.suite());
    suite.addDemo(net.sf.anathema.demo.character.equipment.item.AllDemos.suite());
    suite.addDemo(net.sf.anathema.demo.character.equipment.statscreation.AllDemos.suite());
    //$JDemo-BEGIN$

    //$JDemo-END$
    return suite;
  }
}