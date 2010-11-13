package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class CoreRulesBeastClawsClaws extends AbstractLunarGiftWeapon {

  public int getAccuracy() {
    return 1;
  }

  public int getDamage() {
    return 3;
  }

  public Integer getDefence() {
    return 1;
  }

  public int getSpeed() {
    return 3;
  }

  public IIdentificate getName() {
    return new Identificate("DeadlyBeastmanTransformation.Weapon.Claws"); //$NON-NLS-1$
  }
}