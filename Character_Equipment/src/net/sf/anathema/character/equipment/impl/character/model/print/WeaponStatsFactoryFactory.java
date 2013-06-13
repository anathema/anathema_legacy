package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.Resources;

public class WeaponStatsFactoryFactory implements IWeaponStatsFactoryFactory {

  @Override
  public IEquipmentStatsDecorationFactory<IWeaponStats> create(Resources resources) {
    return new WeaponStatsDecorationFactory(resources);
  }
}