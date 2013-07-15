package net.sf.anathema.character.equipment.character.model.stats.modification;

public enum WeaponStatsType {

  Bow(true), Thrown(true), Melee(false), Flame(true), Thrown_BowBonuses(true);

  private boolean isRanged;

  private WeaponStatsType(boolean isRanged) {
    this.isRanged = isRanged;
  }

  public boolean isRanged() {
    return isRanged;
  }
}