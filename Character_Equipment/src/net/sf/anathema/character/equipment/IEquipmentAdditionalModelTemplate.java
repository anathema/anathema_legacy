package net.sf.anathema.character.equipment;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;

public interface IEquipmentAdditionalModelTemplate {
  public static final String ID = "Equipment"; //$NON-NLS-1$

  public IEquipmentTemplate getNaturalWeaponTemplate(ICharacterType characterType);

  public void addNaturalWeaponTemplate(ICharacterType type, IEquipmentTemplate template);
}