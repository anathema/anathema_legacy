package net.sf.anathema.character.reporting.pdf.content.stats;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.SubContent;

public interface FixedLineStatsContent<STATS extends IStats> extends SubContent {

  int getLineCount();

  STATS[] getPrintStats();

  IStatsGroup<STATS>[] createStatsGroups();
}
