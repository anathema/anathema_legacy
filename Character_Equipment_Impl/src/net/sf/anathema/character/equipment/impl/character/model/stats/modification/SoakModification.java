package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class SoakModification implements StatsModification {

  private final StatModifier modifier;

  public SoakModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int bonus = modifier.calculate();
    return input + bonus;
  }
}