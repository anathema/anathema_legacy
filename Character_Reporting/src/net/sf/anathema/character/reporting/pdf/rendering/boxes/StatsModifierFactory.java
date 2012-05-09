package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;

public class StatsModifierFactory {
  public static ICharacterStatsModifiers create(IGenericCharacter character) {
    IEquipmentAdditionalModel equipment = (IEquipmentAdditionalModel) character.getAdditionalModel(
            IEquipmentAdditionalModelTemplate.ID);
    return equipment.createStatsModifiers(character);
  }
}