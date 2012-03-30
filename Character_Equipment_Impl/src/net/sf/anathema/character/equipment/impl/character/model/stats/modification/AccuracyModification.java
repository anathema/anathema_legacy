package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class AccuracyModification implements StatsModification {

  private final StatModifier modifier;

  public AccuracyModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int bonus = modifier.calculate();
    return input + bonus;
  }
}