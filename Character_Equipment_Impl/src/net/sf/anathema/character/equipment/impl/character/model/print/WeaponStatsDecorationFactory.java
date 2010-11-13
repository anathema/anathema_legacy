package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.WeaponStatsDecorator;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public class WeaponStatsDecorationFactory implements IEquipmentStatsDecorationFactory<IWeaponStats> {

  private final EquipmentPrintNameFactory nameFactory;

  public WeaponStatsDecorationFactory(IResources resources) {
    this.nameFactory = new EquipmentPrintNameFactory(resources);
  }

  @Override
  public IWeaponStats createRenamedPrintDecoration(IEquipmentItem item, final IWeaponStats stats) {
    return new WeaponStatsDecorator(stats, nameFactory.create(item, stats));
  }
}