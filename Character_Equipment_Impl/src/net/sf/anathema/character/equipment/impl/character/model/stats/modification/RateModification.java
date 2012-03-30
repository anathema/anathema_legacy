package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class RateModification implements StatsModification {

  private final StatModifier modifier;

  public RateModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int bonus = modifier.calculate();
    return Math.min(5, input + bonus);
  }
}