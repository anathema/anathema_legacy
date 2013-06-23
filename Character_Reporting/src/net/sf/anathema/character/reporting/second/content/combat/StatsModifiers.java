package net.sf.anathema.character.reporting.second.content.combat;

import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.reporting.pdf.model.StatsModelFetcher;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.StatsModifierFactory;
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