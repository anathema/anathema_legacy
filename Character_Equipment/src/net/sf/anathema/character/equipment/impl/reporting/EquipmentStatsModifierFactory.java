package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.NaturalWeaponMap;
import net.sf.anathema.hero.equipment.model.EquipmentModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.StatsModifierFactory;

public class EquipmentStatsModifierFactory implements StatsModifierFactory {
  @Override
  public ICharacterStatsModifiers create(IGenericCharacter character) {
    EquipmentModel equipment = (EquipmentModel) character.getAdditionalModel(
            NaturalWeaponMap.ID);
    return equipment.createStatsModifiers(character);
  }
}