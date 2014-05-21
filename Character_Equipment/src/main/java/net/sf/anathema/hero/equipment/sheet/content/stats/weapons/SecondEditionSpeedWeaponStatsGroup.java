package net.sf.anathema.hero.equipment.sheet.content.stats.weapons;

import net.sf.anathema.character.framework.library.HeroStatsModifiers;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.framework.environment.Resources;

public class SecondEditionSpeedWeaponStatsGroup extends AbstractSpeedWeaponStatsGroup {

  public SecondEditionSpeedWeaponStatsGroup(Resources resources, HeroStatsModifiers equipment) {
    super(resources, equipment);
  }

  @Override
  protected int getSpeedValue(IWeaponStats weapon, HeroStatsModifiers equipment) {
    return weapon.getSpeed();
  }
}