package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public interface IWeaponStatsFactoryFactory {

  public IEquipmentStatsDecorationFactory<IWeaponStats> create(IResources resources);
}