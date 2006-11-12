package net.sf.anathema.character.equipment;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.type.CharacterType;

public interface IEquipmentAdditionalModelTemplate {
  public static final String ID = "Equipment"; //$NON-NLS-1$

  public IEquipmentTemplate getNaturalWeaponTemplate(CharacterType characterType);

  public void addNaturalWeaponTemplate(CharacterType type, IEquipmentTemplate template);
}