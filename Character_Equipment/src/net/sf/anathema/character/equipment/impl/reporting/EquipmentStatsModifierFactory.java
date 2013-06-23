package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.StatsModifierFactory;

public class EquipmentStatsModifierFactory implements StatsModifierFactory {
  @Override
  public ICharacterStatsModifiers create(IGenericCharacter character) {
    IEquipmentAdditionalModel equipment = (IEquipmentAdditionalModel) character.getAdditionalModel(
            IEquipmentAdditionalModelTemplate.ID);
    return equipment.createStatsModifiers(character);
  }
}