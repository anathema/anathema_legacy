package net.sf.anathema.character.reporting.sheet.common;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.common.encoder.AbstractTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.common.stats.IStatsGroup;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.elements.TableCell;

public abstract class AbstractStatsTableEncoder<T extends IStats> extends AbstractTableEncoder {

  private final Font font;
  private final Font sectionFont;
  private final Font headerFont;

  public AbstractStatsTableEncoder(BaseFont baseFont) {
    this.headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.sectionFont = new Font(font);
    this.sectionFont.setStyle(Font.BOLD);
  }

  protected final Font getFont() {
    return font;
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups(character);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable table = new PdfPTable(columnWidths);
    table.setTotalWidth(bounds.width);
    for (int index = 0; index < groups.length; index++) {
      Font usedFont = index == 0 ? font : headerFont;
      table.addCell(createHeaderCell(
          groups[index].getTitle(),
          groups[index].getColumnCount(),
          index != groups.length - 1,
          usedFont));
    }
    encodeContent(table, character, bounds);
    return table;
  }

  protected abstract void encodeContent(PdfPTable table, IGenericCharacter character, Bounds bounds);

  protected abstract IStatsGroup<T>[] createStatsGroups(IGenericCharacter character);

  protected final void encodeContentLine(PdfPTable table, IStatsGroup<T>[] groups, T stats) {
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        table.addCell(createSpaceCell());
      }
      groups[index].addContent(table, font, stats);
    }
  }

  protected final void encodeSectionLine(PdfPTable table, String sectionName) {
    int columnCount = table.getAbsoluteWidths().length;
    Phrase phrase = new Phrase(sectionName, sectionFont);
    PdfPCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPaddingTop(3);
    cell.setPaddingLeft(0.75f);
    cell.setColspan(columnCount);
    table.addCell(cell);
  }

  protected final float[] calculateColumnWidths(IStatsGroup<T>[] groups) {
    Float[] columnWidths = new Float[0];
    for (IStatsGroup<T> group : groups) {
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
