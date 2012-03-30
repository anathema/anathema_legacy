package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class FatigueModification implements StatsModification {

  private final StatModifier modifier;

  public FatigueModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int original) {
    int bonus = modifier.calculate();
    return original - bonus;
  }
}