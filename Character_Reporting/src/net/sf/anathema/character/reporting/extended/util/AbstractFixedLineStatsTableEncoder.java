package net.sf.anathema.character.reporting.extended.util;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.stats.IStatsGroup;
import net.sf.anathema.character.reporting.extended.common.AbstractStatsTableEncoder;

public abstract class AbstractFixedLineStatsTableEncoder<T extends IStats> extends AbstractStatsTableEncoder<T> {

  public AbstractFixedLineStatsTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @Override
  protected void encodeContent(PdfPTable table, IGenericCharacter character, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups(character);
    T[] printStats = getPrintStats(character);
    int line = 0;
    while (line < getLineCount()) {
      T printStat = line < printStats.length ? printStats[line] : null;
      encodeContentLine(table, groups, printStat);
      line++;
    }
  }

  protected abstract int getLineCount();

  protected abstract T[] getPrintStats(IGenericCharacter character);
}
