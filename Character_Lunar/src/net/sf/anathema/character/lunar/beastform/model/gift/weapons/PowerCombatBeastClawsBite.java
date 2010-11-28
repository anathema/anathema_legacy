package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class PowerCombatBeastClawsBite extends AbstractLunarGiftWeapon {

  public int getSpeed() {
    return -6;
  }

  public int getAccuracy() {
    return -1;
  }

  public int getDamage() {
    return 8;
  }

  public Integer getDefence() {
    return -1;
  }

  @Override
  public Integer getRate() {
    return 2;
  }

  public IIdentificate getName() {
    return new Identificate("DeadlyBeastmanTransformation.Weapon.Bite"); //$NON-NLS-1$
  }
}