package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSpeedWeaponStatsGroup extends AbstractSpeedWeaponStatsGroup {

  public SecondEditionSpeedWeaponStatsGroup(IResources resources, ICharacterStatsModifiers equipment) {
    super(resources, equipment);
  }

  @Override
  protected int getSpeedValue(IWeaponStats weapon, ICharacterStatsModifiers equipment) {
    return weapon.getSpeed();
  }
}