package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public enum WeaponStatsType {

  Bow(true), Thrown(true), Melee(false), Flame(true);
  
  private boolean isRanged;
  
  private WeaponStatsType(boolean isRanged) {
    this.isRanged = isRanged;
  }
  
  public boolean isRanged() {
    return isRanged;
  }
}