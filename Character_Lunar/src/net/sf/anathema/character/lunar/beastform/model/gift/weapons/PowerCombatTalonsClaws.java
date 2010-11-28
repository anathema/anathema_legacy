package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class PowerCombatTalonsClaws extends AbstractLunarGiftWeapon {

  public int getAccuracy() {
    return 3;
  }

  public int getDamage() {
    return 7;
  }

  public Integer getDefence() {
    return 2;
  }

  public int getSpeed() {
    return 2;
  }

  @Override
  public Integer getRate() {
    return 7;
  }

  public IIdentificate getName() {
    return new Identificate("DeadlyBeastmanTransformation.Weapon.Claws"); //$NON-NLS-1$
  }
}