package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class DamageModification implements StatsModification {

  private final StatModifier modifier;

  public DamageModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int bonus = modifier.calculate();
    return input + bonus;
  }

}