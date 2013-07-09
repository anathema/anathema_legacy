package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;

public class StatsModifiers {

  public static ICharacterStatsModifiers allStatsModifiers(Hero hero) {
    AggregatedStatsModifiers allModifiers = new AggregatedStatsModifiers();
    for (StatsModifierFactory factory : StatsModelFetcher.fetch(hero).getModifierFactories()) {
      ICharacterStatsModifiers statsModifiers = factory.createStatsModifiers(hero);
      allModifiers.add(statsModifiers);
    }
    return allModifiers;
  }
}