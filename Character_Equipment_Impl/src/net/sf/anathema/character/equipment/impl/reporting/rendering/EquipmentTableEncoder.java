package net.sf.anathema.character.equipment.impl.reporting.rendering;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.FixedLineStatsContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractFixedLineStatsTableEncoder;

public class EquipmentTableEncoder<
  STATS extends IEquipmentStats, CONTENT extends FixedLineStatsContent<STATS>> extends AbstractFixedLineStatsTableEncoder<STATS> {

  private Class<? extends CONTENT> contentClass;

  public EquipmentTableEncoder(Class<? extends CONTENT> contentClass) {
    this.contentClass = contentClass;
  }

  @Override
  protected final int getLineCount(ReportSession session) {
    return createContent(session).getLineCount();
  }

  @Override
  protected final STATS[] getPrintStats(ReportSession session) {
    return createContent(session).getPrintStats();
  }

  @Override
  protected final IStatsGroup<STATS>[] createStatsGroups(ReportSession session) {
    return createContent(session).createStatsGroups();
  }

  protected final CONTENT createContent(ReportSession session) {
    return session.createContent(contentClass);
  }
}
