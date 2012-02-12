package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.util.Identificate;

import java.util.Collection;
import java.util.Collections;

public class EquipmentAdditionalModelTemplate extends Identificate implements
        IGlobalAdditionalTemplate,
        IEquipmentAdditionalModelTemplate {

  private Instantiater instantiater;

  public EquipmentAdditionalModelTemplate(Instantiater instantiater) {
    super(ID);
    this.instantiater = instantiater;
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }

  public IEquipmentTemplate getNaturalWeaponTemplate(ICharacterType characterType) {
    Collection<IEquipmentTemplate> templates = gatherTemplates();
    for (IEquipmentTemplate template : templates) {
      if (template.getClass().getAnnotation(RegisteredNaturalWeapon.class).characterType() == characterType) {
        return template;
      }
    }
    return null;
  }

  private Collection<IEquipmentTemplate> gatherTemplates() {
    try {
      return instantiater.instantiateAll(RegisteredNaturalWeapon.class);
    } catch (InitializationException e) {
      Logger.getLogger(EquipmentAdditionalModel.class).error("Could not collect additional natural weapons.", e);
      return Collections.emptyList();
    }
  }
}