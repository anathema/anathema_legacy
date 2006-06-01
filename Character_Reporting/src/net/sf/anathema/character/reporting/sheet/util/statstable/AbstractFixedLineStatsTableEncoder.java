package net.sf.anathema.character.reporting.sheet.util.statstable;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractFixedLineStatsTableEncoder<T extends IStats> extends AbstractStatsTableEncoder<T> {

  public AbstractFixedLineStatsTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @Override
  protected void encodeContent(PdfPTable table, IGenericCharacter character, Bounds bounds, IStatsGroup<T>[] groups) {
    T[] printStats = getPrintStats(character);
    int line = 0;
    while (line < getLineCount()) {
      T printStat = line < printStats.length ? printStats[line] : null;
      encodeContentLine(table, groups, printStat);
      line++;
    }
  }

  protected abstract int getLineCount();

  protected abstract IGenericTrait getTrait(IGenericCharacter character, T equipment);

  protected abstract T[] getPrintStats(IGenericCharacter character);
}