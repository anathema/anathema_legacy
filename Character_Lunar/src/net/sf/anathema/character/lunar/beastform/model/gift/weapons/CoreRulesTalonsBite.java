package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class CoreRulesTalonsBite extends AbstractLunarGiftWeapon {

  public int getAccuracy() {
    return 2;
  }

  public int getDamage() {
    // TODO Auto-generated method stub
    return 8;
  }

  public Integer getDefence() {
    return 0;
  }

  public int getSpeed() {
    return 3;
  }

  public IIdentificate getName() {
    return new Identificate("DeadlyBeastmanTransformation.Weapon.Bite"); //$NON-NLS-1$
  }
}