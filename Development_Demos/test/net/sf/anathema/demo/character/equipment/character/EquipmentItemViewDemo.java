package net.sf.anathema.demo.character.equipment.character;

import de.jdemo.junit.DemoAsTestRunner;
import net.sf.anathema.character.equipment.impl.character.view.EquipmentObjectView;
import de.jdemo.extensions.SwingDemoCase;
import org.junit.runner.RunWith;

@RunWith(DemoAsTestRunner.class)
public class EquipmentItemViewDemo extends SwingDemoCase {

  public void demo() {
    EquipmentObjectView view = new EquipmentObjectView();
    view.setItemTitle("Title"); //$NON-NLS-1$
    view.setItemDescription("Ganz viel description"); //$NON-NLS-1$
    view.addStats("Boese Waffe: Viel, viel Schaden"); //$NON-NLS-1$
    view.addStats("Abyssal-Waffe: Jetzt auch mit aggrevated Schaden."); //$NON-NLS-1$
    show(view.getTaskGroup());
  }
}