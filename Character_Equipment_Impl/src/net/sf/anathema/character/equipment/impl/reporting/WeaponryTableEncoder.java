package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.FixedLineStatsContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;

public class WeaponryTableEncoder extends AbstractEquipmentTableEncoder<IWeaponStats> {

  private Class<? extends FixedLineStatsContent<IWeaponStats>> contentClass;

  public WeaponryTableEncoder(Class<? extends FixedLineStatsContent<IWeaponStats>> contentClass, BaseFont baseFont) {
    super(baseFont);
    this.contentClass = contentClass;
  }

  @Override
  protected int getLineCount(ReportContent content) {
    return createContent(content).getLineCount();
  }

  @Override
  protected IWeaponStats[] getPrintStats(ReportContent content) {
    return createContent(content).getPrintStats();
  }

  @Override
  protected IStatsGroup<IWeaponStats>[] createStatsGroups(ReportContent content) {
    return createContent(content).createStatsGroups();
  }
  
  private FixedLineStatsContent<IWeaponStats> createContent(ReportContent content) {
    return content.createSubContent(contentClass);
  }
}
