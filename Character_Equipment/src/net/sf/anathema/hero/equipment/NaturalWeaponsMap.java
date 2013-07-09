package net.sf.anathema.hero.equipment;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.equipment.core.IEquipmentTemplate;

public interface NaturalWeaponsMap {
  String ID = "Equipment";

  IEquipmentTemplate getNaturalWeaponTemplate(ICharacterType characterType);
}