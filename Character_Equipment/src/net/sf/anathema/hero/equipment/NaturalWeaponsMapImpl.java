package net.sf.anathema.hero.equipment;

import net.sf.anathema.character.equipment.character.model.RegisteredNaturalWeapon;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.logging.Logger;

import java.util.Collection;
import java.util.Collections;

public class NaturalWeaponsMapImpl implements NaturalWeaponsMap {

  private CharacterTypes characterTypes;
  private ObjectFactory objectFactory;

  public NaturalWeaponsMapImpl(CharacterTypes characterTypes, ObjectFactory objectFactory) {
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