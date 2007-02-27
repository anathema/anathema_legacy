package net.sf.anathema.character.equipment.impl.character.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identificate;

public class EquipmentAdditonalModelTemplate extends Identificate implements
    IGlobalAdditionalTemplate,
    IEquipmentAdditionalModelTemplate {

  public final Map<ICharacterType, IEquipmentTemplate> templatesByType = new HashMap<ICharacterType, IEquipmentTemplate>();

  public EquipmentAdditonalModelTemplate() {
    super(ID);
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }

  public IEquipmentTemplate getNaturalWeaponTemplate(ICharacterType characterType) {
    return templatesByType.get(characterType);
  }

  public void addNaturalWeaponTemplate(ICharacterType type, IEquipmentTemplate template) {
    templatesByType.put(type, template);
  }
}