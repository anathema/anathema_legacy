package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class AttunementModifier implements StatModifier {
  private final StatModifier modifier;
  private final boolean attuned;

  public AttunementModifier(StatModifier modifier, boolean isAttuned) {
    this.modifier = modifier;
    attuned = isAttuned;
  }

  @Override
  public int getModifier() {
    if (!attuned) {
      return 0;
    }
    return modifier.getModifier();
  }
}