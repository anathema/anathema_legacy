package net.sf.anathema.character.reporting.pdf.rendering.general.stats;

import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public abstract class AbstractFixedLineStatsTableEncoder<T extends IStats> extends AbstractStatsTableEncoder<T, ReportContent> {

  @Override
  protected void encodeContent(SheetGraphics graphics, PdfPTable table, ReportContent content, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups(content);
    T[] printStats = getPrintStats(content);
    int line = 0;
    while (line < getLineCount(content)) {
      T printStat = line < printStats.length ? printStats[line] : null;
      encodeContentLine(graphics, table, groups, printStat);
      line++;
    }
  }

  protected abstract int getLineCount(ReportContent content);

  protected abstract T[] getPrintStats(ReportContent content);
}
