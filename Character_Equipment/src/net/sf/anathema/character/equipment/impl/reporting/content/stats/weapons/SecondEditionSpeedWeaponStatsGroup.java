package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionSpeedWeaponStatsGroup extends AbstractSpeedWeaponStatsGroup {

  public SecondEditionSpeedWeaponStatsGroup(Resources resources, ICharacterStatsModifiers equipment) {
    super(resources, equipment);
  }

  @Override
  protected int getSpeedValue(IWeaponStats weapon, ICharacterStatsModifiers equipment) {
    return weapon.getSpeed();
  }
}