package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public interface IStatsModification {

  public abstract int getModifiedValue(int input, WeaponStatsType type);

}