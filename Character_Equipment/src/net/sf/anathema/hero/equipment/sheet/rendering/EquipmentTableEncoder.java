package net.sf.anathema.hero.equipment.sheet.rendering;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.hero.sheet.pdf.content.stats.FixedLineStatsContent;
import net.sf.anathema.hero.sheet.pdf.content.stats.IStatsGroup;
import net.sf.anathema.hero.sheet.pdf.encoder.stats.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

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
