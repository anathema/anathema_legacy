package net.sf.anathema.character.equipment.impl.reporting.second.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.AbstractSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSpeedWeaponStatsGroup extends AbstractSpeedWeaponStatsGroup {

  public SecondEditionSpeedWeaponStatsGroup(IResources resources) {
    super(resources);
  }

  @Override
  protected int getSpeedValue(IWeaponStats weapon) {
    return weapon.getSpeed();
  }
}