package net.sf.anathema.character.equipment.impl.character.model.stats.modification.modifier;

import net.sf.anathema.character.equipment.impl.character.model.stats.modification.StatModifier;

public class BestModifier implements StatModifier {

  private final StatModifier[] modifiers;

  public BestModifier(StatModifier... modifiers) {
    this.modifiers = modifiers;
  }

  @Override
  public int calculate() {
    int result = 0;
    for (StatModifier modifier : modifiers) {
      int candidate = modifier.calculate();
      if (result < candidate) {
        result = candidate;
      }
    }
    return result;
  }
}
