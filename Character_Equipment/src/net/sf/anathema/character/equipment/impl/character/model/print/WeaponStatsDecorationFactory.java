package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.WeaponStatsDecorator;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.Resources;

public class WeaponStatsDecorationFactory implements IEquipmentStatsDecorationFactory<IWeaponStats> {

  private final EquipmentPrintNameFactory nameFactory;

  public WeaponStatsDecorationFactory(Resources resources) {
    this.nameFactory = new EquipmentPrintNameFactory(resources);
  }

  @Override
  public IWeaponStats createRenamedPrintDecoration(IEquipmentItem item, IWeaponStats stats) {
    return new WeaponStatsDecorator(stats, nameFactory.create(item, stats));
  }
}