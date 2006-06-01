package net.sf.anathema.character.reporting.sheet.util.statstable;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractStatsTableEncoder<T extends IStats> extends AbstractTableEncoder {

  private final Font font;
  private final Font headerFont;

  public AbstractStatsTableEncoder(BaseFont baseFont) {
    this.headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    this.font = TableEncodingUtilities.createFont(baseFont);
  }

  protected final Font getFont() {
    return font;
  }

  @Override
  protected PdfPTable createTable(IGenericCharacter character, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups(character);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    for (int index = 0; index < groups.length; index++) {
      Font usedFont = index == 0 ? font : headerFont;
      table.addCell(createHeaderCell(
          groups[index].getTitle(),
          groups[index].getColumnCount(),
          index != groups.length - 1,
          usedFont));
    }
    T[] printStats = getPrintStats(character);
    int line = 0;
    while(isLineValid(line, bounds, table)) {
      T printStat = line < printStats.length ? printStats[line] : null;
      encodeContentLine(table, groups, printStat);
      line++;
    }
    return table;
  }

  protected abstract boolean isLineValid(int line, Bounds bounds, PdfPTable table);

  protected abstract IGenericTrait getTrait(IGenericCharacter character, T equipment);

  protected abstract T[] getPrintStats(IGenericCharacter character);

  protected abstract IStatsGroup<T>[] createStatsGroups(IGenericCharacter character);

  private void encodeContentLine(PdfPTable table, IStatsGroup<T>[] groups, T equipment) {
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        table.addCell(createSpaceCell());
      }
      groups[index].addContent(table, font, equipment);
    }
  }

  private float[] calculateColumnWidths(IStatsGroup[] groups) {
    Float[] columnWidths = new Float[0];
    for (IStatsGroup group : groups) {
      if (columnWidths.length != 0) {
        columnWidths = ArrayUtilities.concat(columnWidths, new Float(0.2));
      }
      columnWidths = ArrayUtilities.concat(columnWidths, group.getColumnWeights());
    }
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(columnWidths);
  }

  protected PdfPCell createSpaceCell() {
    PdfPCell cell = new PdfPCell(new Phrase("", font)); //$NON-NLS-1$
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private PdfPCell createHeaderCell(String text, int columnSpan, boolean useSpaceCell, Font textFont) {
    PdfPCell cell = new PdfPCell(new Phrase(text, textFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(useSpaceCell ? columnSpan + 1 : columnSpan);
    cell.setPaddingLeft(0);
    cell.setPaddingRight(0);
    return cell;
  }
}