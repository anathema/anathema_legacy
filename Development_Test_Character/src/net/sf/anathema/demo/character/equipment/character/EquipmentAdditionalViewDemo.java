package net.sf.anathema.demo.character.equipment.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentObject;
import net.sf.anathema.character.equipment.impl.character.model.natural.Kick;
import net.sf.anathema.character.equipment.impl.character.model.natural.Punch;
import net.sf.anathema.character.equipment.impl.character.view.EquipmentAdditionalView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.dummy.character.additional.DemoEquipmentAdditionalModel;
import net.sf.anathema.framework.resources.AnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class EquipmentAdditionalViewDemo extends SwingDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    DemoEquipmentAdditionalModel equipmentModel = new DemoEquipmentAdditionalModel();
    IEquipmentStats[] equipments = new IEquipmentStats[] { new Punch(), new Kick() };
    equipmentModel.addAvailableObject(new EquipmentObject(equipments, "Character Data", //$NON-NLS-1$
        "The characters natural weapons and armour.")); //$NON-NLS-1$
    EquipmentAdditionalView view = new EquipmentAdditionalView();
    new EquipmentAdditionalPresenter(resources, equipmentModel, view).initPresentation();
    show(view.getComponent());
  }
}