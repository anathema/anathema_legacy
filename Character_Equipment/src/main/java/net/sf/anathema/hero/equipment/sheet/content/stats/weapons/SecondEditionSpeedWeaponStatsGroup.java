package net.sf.anathema.hero.equipment.sheet.content.stats.weapons;

import net.sf.anathema.character.main.util.HeroStatsModifiers;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionSpeedWeaponStatsGroup extends AbstractSpeedWeaponStatsGroup {

  public SecondEditionSpeedWeaponStatsGroup(Resources resources, HeroStatsModifiers equipment) {
    super(resources, equipment);
  }

  @Override
  protected int getSpeedValue(IWeaponStats weapon, HeroStatsModifiers equipment) {
    return weapon.getSpeed();
  }
}