package net.sf.anathema.character.equipment.reporting;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;

public class EquipmentStatsModifierFactory implements StatsModifierFactory {
  @Override
  public ICharacterStatsModifiers create(Hero hero) {
    EquipmentModel equipment = EquipmentModelFetcher.fetch(hero);
    return equipment.createStatsModifiers(hero);
  }
}