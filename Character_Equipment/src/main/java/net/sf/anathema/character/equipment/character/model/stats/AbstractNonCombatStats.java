package net.sf.anathema.character.equipment.character.model.stats;

public abstract class AbstractNonCombatStats extends AbstractStats {
  @Override
  public boolean representsItemForUseInCombat() {
    return false;
  }
}
