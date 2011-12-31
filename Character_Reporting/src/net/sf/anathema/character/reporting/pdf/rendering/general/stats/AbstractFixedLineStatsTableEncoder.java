package net.sf.anathema.character.reporting.pdf.rendering.general.stats;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;

public abstract class AbstractFixedLineStatsTableEncoder<T extends IStats> extends AbstractStatsTableEncoder<T, ReportContent> {

  public AbstractFixedLineStatsTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @Override
  protected void encodeContent(PdfPTable table, ReportContent content, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups(content);
    T[] printStats = getPrintStats(content);
    int line = 0;
    while (line < getLineCount(content)) {
      T printStat = line < printStats.length ? printStats[line] : null;
      encodeContentLine(table, groups, printStat);
      line++;
    }
  }

  protected abstract int getLineCount(ReportContent content);

  protected abstract T[] getPrintStats(ReportContent content);
}
