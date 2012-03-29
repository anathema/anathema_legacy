package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class RangeModification implements StatsModification {

  private final StatModifier modifier;

  public RangeModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int bonus = modifier.calculate();
    return input + bonus;
  }
}