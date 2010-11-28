package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class PowerCombatTalonsBite extends AbstractLunarGiftWeapon {

  public int getAccuracy() {
    return 0;
  }

  public int getDamage() {
    return 10;
  }

  public Integer getDefence() {
    return 0;
  }

  @Override
  public Integer getRate() {
    return 2;
  }

  public int getSpeed() {
    return -4;
  }

  public IIdentificate getName() {
    return new Identificate("DeadlyBeastmanTransformation.Weapon.Bite"); //$NON-NLS-1$
  }
}