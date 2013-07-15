package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.character.main.equipment.HeroStatsModifiers;
import net.sf.anathema.hero.model.Hero;

public interface StatsModifierFactory  {

  HeroStatsModifiers createStatsModifiers(Hero hero);
}