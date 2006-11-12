package net.sf.anathema.character.equipment.impl.character.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.Identificate;

public class EquipmentAdditonalModelTemplate extends Identificate implements
    IGlobalAdditionalTemplate,
    IEquipmentAdditionalModelTemplate {

  public final Map<CharacterType, IEquipmentTemplate> templatesByType = new HashMap<CharacterType, IEquipmentTemplate>();

  public EquipmentAdditonalModelTemplate() {
    super(ID);
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }

  public IEquipmentTemplate getNaturalWeaponTemplate(CharacterType characterType) {
    return templatesByType.get(characterType);
  }

  public void addNaturalWeaponTemplate(CharacterType type, IEquipmentTemplate template) {
    templatesByType.put(type, template);
  }
}