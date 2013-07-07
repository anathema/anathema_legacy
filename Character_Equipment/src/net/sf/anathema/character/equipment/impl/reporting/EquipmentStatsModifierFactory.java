package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.StatsModifierFactory;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class EquipmentStatsModifierFactory implements StatsModifierFactory {
  @Override
  public ICharacterStatsModifiers create(Hero hero) {
    EquipmentModel equipment = EquipmentModelFetcher.fetch(hero);
    return equipment.createStatsModifiers(hero);
  }
}