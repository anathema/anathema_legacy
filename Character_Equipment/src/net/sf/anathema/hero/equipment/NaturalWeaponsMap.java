package net.sf.anathema.hero.equipment;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.main.type.ICharacterType;

public interface NaturalWeaponsMap {
  String ID = "Equipment";

  IEquipmentTemplate getNaturalWeaponTemplate(ICharacterType characterType);
}