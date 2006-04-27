package net.sf.anathema.test.character.equipment.statscreation;

import de.jdemo.framework.DemoSuite;
import de.jdemo.framework.IDemo;

public class AllDemos {

  public static IDemo suite() {
    DemoSuite suite = new DemoSuite("Demo for net.sf.anathema.test.character.equipment.statscreation"); //$NON-NLS-1$
    //$JDemo-BEGIN$
    suite.addDemo(new DemoSuite(EquipmentStatisticsCreationWizardDemo.class));
    //$JDemo-END$
    return suite;
  }
}