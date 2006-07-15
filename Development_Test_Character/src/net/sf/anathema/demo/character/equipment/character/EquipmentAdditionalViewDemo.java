package net.sf.anathema.demo.character.equipment.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentObject;
import net.sf.anathema.character.equipment.impl.character.model.natural.Kick;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.Punch;
import net.sf.anathema.character.equipment.impl.character.view.EquipmentAdditionalView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.dummy.character.additional.DemoEquipmentAdditionalModel;
import net.sf.anathema.dummy.character.trait.DummyGenericTrait;
import net.sf.anathema.framework.resources.AnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class EquipmentAdditionalViewDemo extends SwingDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    DemoEquipmentAdditionalModel equipmentModel = new DemoEquipmentAdditionalModel();
    IEquipment[] equipments = new IEquipment[] {
        new NaturalSoak(new DummyGenericTrait(AttributeType.Stamina, 3), CharacterType.SOLAR),
        new Punch(),
        new Kick() };
    equipmentModel.addObject(new EquipmentObject(
        equipments,
        "Character Data",
        "The characters natural weapons and armour."));
    EquipmentAdditionalView view = new EquipmentAdditionalView();
    new EquipmentAdditionalPresenter(resources, equipmentModel, view).initPresentation();
    show(view.getComponent());
  }
}