package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.character.main.equipment.HeroStatsModifiers;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;

public class StatsModifiers {

  public static HeroStatsModifiers allStatsModifiers(Hero hero) {
    AggregatedStatsModifiers allModifiers = new AggregatedStatsModifiers();
    for (StatsModifierFactory factory : StatsModelFetcher.fetch(hero).getModifierFactories()) {
      HeroStatsModifiers statsModifiers = factory.createStatsModifiers(hero);
      allModifiers.add(statsModifiers);
    }
    return allModifiers;
  }
}