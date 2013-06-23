package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.Collection;
import java.util.Collections;

public class EquipmentAdditionalModelTemplate extends SimpleIdentifier implements IGlobalAdditionalTemplate, IEquipmentAdditionalModelTemplate {

  private CharacterTypes characterTypes;
  private ObjectFactory objectFactory;

  public EquipmentAdditionalModelTemplate(CharacterTypes characterTypes, ObjectFactory objectFactory) {
    super(ID);
    this.characterTypes = characterTypes;
    this.objectFactory = objectFactory;
  }

  @Override
  public IEquipmentTemplate getNaturalWeaponTemplate(ICharacterType characterType) {
    Collection<IEquipmentTemplate> templates = gatherTemplates();
    for (IEquipmentTemplate template : templates) {
      String applicableType = template.getClass().getAnnotation(RegisteredNaturalWeapon.class).characterType();
      if (characterTypes.findById(applicableType).equals(characterType)) {
        return template;
      }
    }
    return null;
  }

  private Collection<IEquipmentTemplate> gatherTemplates() {
    try {
      return objectFactory.instantiateAll(RegisteredNaturalWeapon.class);
    } catch (InitializationException e) {
      Logger.getLogger(EquipmentModelImpl.class).error("Could not collect additional natural weapons.", e);
      return Collections.emptyList();
    }
  }
}