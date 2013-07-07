package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.hero.model.Hero;

public interface StatsModifierFactory  {

  ICharacterStatsModifiers create(Hero hero);
}
