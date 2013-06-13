package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;

public interface StatsModifierFactory {
  ICharacterStatsModifiers create(IGenericCharacter character);
}
