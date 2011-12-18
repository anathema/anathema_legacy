package net.sf.anathema.character.equipment.impl.reporting.stats.weapons;

import net.sf.anathema.character.equipment.impl.reporting.stats.weapons.AbstractSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSpeedWeaponStatsGroup extends AbstractSpeedWeaponStatsGroup {

  public SecondEditionSpeedWeaponStatsGroup(IResources resources, IEquipmentModifiers equipment) {
    super(resources, equipment);
  }

  @Override
  protected int getSpeedValue(IWeaponStats weapon, IEquipmentModifiers equipment) {
    return Math.max(3, weapon.getSpeed() +
    	(weapon.isRangedCombat() ? equipment.getRangedSpeedMod() : equipment.getMeleeSpeedMod()));
  }
}