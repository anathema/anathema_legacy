package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class PowerCombatBeastClawsClaws extends AbstractLunarGiftWeapon {

  public int getSpeed() {
    return 2;
  }

  public int getAccuracy() {
    return 1;
  }

  public int getDamage() {
    return 5;
  }

  public Integer getDefence() {
    return 1;
  }

  @Override
  public Integer getRate() {
    return 4;
  }

  public IIdentificate getName() {
    return new Identificate("DeadlyBeastmanTransformation.Weapon.Claws"); //$NON-NLS-1$
  }
}
