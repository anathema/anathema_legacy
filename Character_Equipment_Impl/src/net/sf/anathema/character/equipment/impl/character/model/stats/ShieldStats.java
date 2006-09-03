package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;

public class ShieldStats extends AbstractStats implements IShieldStats {

  private int rangedCombatDv;
  private int closeCombatDv;

  public int getCloseCombatDvBonus() {
    return closeCombatDv;
  }

  public int getRangedCombatDvBonus() {
    return rangedCombatDv;
  }

  public void setCloseCombatDv(int closeCombatDv) {
    this.closeCombatDv = closeCombatDv;
  }

  public void setRangedCombatDv(int rangedCombatDv) {
    this.rangedCombatDv = rangedCombatDv;
  }
}