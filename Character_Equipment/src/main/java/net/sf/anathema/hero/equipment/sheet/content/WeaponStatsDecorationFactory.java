package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.stats.WeaponStatsDecorator;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
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