package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import net.sf.anathema.character.equipment.impl.reporting.rendering.EquipmentTableEncoder;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.FixedLineStatsContent;

public class WeaponryTableEncoder<C extends FixedLineStatsContent<IWeaponStats>> extends EquipmentTableEncoder<IWeaponStats, C> {

  public WeaponryTableEncoder(Class<C> contentClass) {
    super(contentClass);
  }
}
