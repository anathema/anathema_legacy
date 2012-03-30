package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class SpeedModification implements StatsModification {

  private final StatModifier modifier;

  public SpeedModification(StatModifier modifier) {
    this.modifier = modifier;
  }

  @Override
  public int getModifiedValue(int input) {
    int modifier = this.modifier.calculate();
    return Math.max(3, input - modifier);
  }
}