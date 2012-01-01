package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.FixedLineStatsContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractFixedLineStatsTableEncoder;

public class EquipmentTableEncoder<
  STATS extends IEquipmentStats, CONTENT extends FixedLineStatsContent<STATS>> extends AbstractFixedLineStatsTableEncoder<STATS> {

  private Class<? extends CONTENT> contentClass;

  public EquipmentTableEncoder(Class<? extends CONTENT> contentClass, BaseFont baseFont) {
    super(baseFont);
    this.contentClass = contentClass;
  }

  @Override
  protected final int getLineCount(ReportContent content) {
    return createContent(content).getLineCount();
  }

  @Override
  protected final STATS[] getPrintStats(ReportContent content) {
    return createContent(content).getPrintStats();
  }

  @Override
  protected final IStatsGroup<STATS>[] createStatsGroups(ReportContent content) {
    return createContent(content).createStatsGroups();
  }

  protected final CONTENT createContent(ReportContent content) {
    return content.createSubContent(contentClass);
  }
}
