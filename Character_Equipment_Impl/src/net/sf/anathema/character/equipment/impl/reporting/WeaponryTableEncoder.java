package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.FixedLineStatsContent;

public class WeaponryTableEncoder<C extends FixedLineStatsContent<IWeaponStats>> extends EquipmentTableEncoder<IWeaponStats, C> {

  public WeaponryTableEncoder(Class<C> contentClass, BaseFont baseFont) {
    super(contentClass,  baseFont);
  }
}
