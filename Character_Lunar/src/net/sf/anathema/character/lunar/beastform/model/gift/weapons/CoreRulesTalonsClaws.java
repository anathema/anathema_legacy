package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class CoreRulesTalonsClaws extends AbstractLunarGiftWeapon {

  public int getAccuracy() {
    return 4;
  }

  public int getDamage() {
    return 5;
  }

  public Integer getDefence() {
    return 4;
  }

  public int getSpeed() {
    return 6;
  }

  public IIdentificate getName() {
    return new Identificate("DeadlyBeastmanTransformation.Weapon.Claws"); //$NON-NLS-1$
  }

}
