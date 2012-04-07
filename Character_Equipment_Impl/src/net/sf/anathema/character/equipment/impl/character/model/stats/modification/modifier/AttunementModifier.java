package net.sf.anathema.character.equipment.impl.character.model.stats.modification.modifier;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class AttunementModifier implements StatModifier {
  private final StatModifier modifier;

  public AttunementModifier(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int calculate() {
    return modifier.calculate();
  }
}