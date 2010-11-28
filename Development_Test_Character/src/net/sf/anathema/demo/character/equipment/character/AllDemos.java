package net.sf.anathema.demo.character.equipment.character;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.demo.character.equipment.character"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(EquipmentAdditionalViewDemo.class));
    suite.addDemo(new DemoSuite(EquipmentItemViewDemo.class));
    //$JDemo-END$
    return suite;
  }
}