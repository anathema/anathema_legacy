package net.sf.anathema.character.reporting.second.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.StatsModifierFactory;

import java.util.List;

public class StatsModifiers {
  public static ICharacterStatsModifiers allStatsModifiers(IGenericCharacter character) {
    AggregatedStatsModifiers allModifiers = new AggregatedStatsModifiers();
    List<StatsModifierFactory> modifiers = character.getAllRegistered(StatsModifierFactory.class);
    for (StatsModifierFactory factory : modifiers) {
      ICharacterStatsModifiers statsModifiers = factory.create(character);
      allModifiers.add(statsModifiers);
    }
    return allModifiers;
  }
}