package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.hero.model.Hero;

public interface StatsModifierFactory  {

  ICharacterStatsModifiers createStatsModifiers(Hero hero);
}