package net.sf.anathema.development.character;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditonalModelTemplate;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;

public class DemoEquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements IEquipmentAdditionalModel {

  private final List<IArmour> armours = new ArrayList<IArmour>();
  private final List<IWeapon> weapons = new ArrayList<IWeapon>();

  public void addPrintArmour(IArmour armour) {
    this.armours.add(armour);
  }

  public void addPrintWeapon(IWeapon weapon) {
    this.weapons.add(weapon);
  }

  public IArmour[] getPrintArmours() {
    return armours.toArray(new IArmour[armours.size()]);
  }

  public IWeapon[] getPrintWeapons() {
    return weapons.toArray(new IWeapon[weapons.size()]);
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public String getTemplateId() {
    return EquipmentAdditonalModelTemplate.ID;
  }
}