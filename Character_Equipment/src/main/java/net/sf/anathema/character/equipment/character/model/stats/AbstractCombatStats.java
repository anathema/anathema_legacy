package net.sf.anathema.character.equipment.character.model.stats;

public abstract class AbstractCombatStats extends AbstractStats {

  @Override
  public boolean representsItemForUseInCombat() {
    return true;
  }
}