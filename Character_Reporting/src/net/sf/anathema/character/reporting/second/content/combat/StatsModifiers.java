package net.sf.anathema.character.reporting.second.content.combat;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.hero.sheet.pdf.content.stats.StatsModelFetcher;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;
import net.sf.anathema.hero.model.Hero;

public class StatsModifiers {

  public static ICharacterStatsModifiers allStatsModifiers(Hero hero) {
    AggregatedStatsModifiers allModifiers = new AggregatedStatsModifiers();
    for (StatsModifierFactory factory : StatsModelFetcher.fetch(hero).getModifierFactories()) {
      ICharacterStatsModifiers statsModifiers = factory.create(hero);
      allModifiers.add(statsModifiers);
    }
    return allModifiers;
  }
}