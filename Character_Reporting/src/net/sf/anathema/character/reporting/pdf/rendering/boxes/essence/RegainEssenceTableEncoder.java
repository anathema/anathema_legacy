package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.essence.RegainEssenceContent;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.RecoveryRow;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;

import java.util.List;

import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_MIDDLE;
import static com.itextpdf.text.Element.ALIGN_RIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class RegainEssenceTableEncoder implements ITableEncoder<RegainEssenceContent> {
  protected static float PADDING = 1f;

  private static final int HEADER_BORDER = Rectangle.NO_BORDER;
  private static final int INTERNAL_BORDER = Rectangle.BOX;

  protected Float[] getEssenceColumns() {
    return new Float[]{6f, 2.5f, 2.5f};
  }

  private float[] createColumnWidth() {
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(getEssenceColumns());
  }

  public final float getTableHeight(RegainEssenceContent content) {
    int lines = content.getOverallLineCount();
    return lines * BARE_LINE_HEIGHT + 1.75f * TEXT_PADDING;
  }

  @Override
  public final float encodeTable(SheetGraphics graphics, RegainEssenceContent content, Bounds bounds) throws DocumentException {
    PdfPTable table = createTable(graphics, content);
    table.setWidthPercentage(100);
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  protected final PdfPTable createTable(SheetGraphics graphics, RegainEssenceContent content) throws DocumentException {
    float[] columnWidth = createColumnWidth();
    PdfPTable table = new PdfPTable(columnWidth);
    addHeader(graphics, table, content);
    addRecoveryRows(graphics, table, content);
    return table;
  }

  private final void addHeader(SheetGraphics graphics, PdfPTable table, RegainEssenceContent content) {
    PdfPCell spaceCell = createSpaceCell(graphics);
    Font font = getDefaultFont(graphics);
    table.addCell(spaceCell);
    table.addCell(createHeaderCell(font, content.getAtEaseHeaderLabel(), 1));
    table.addCell(createHeaderCell(font, content.getRelaxHeaderLabel(), 1));
  }

  private void addRecoveryRows(SheetGraphics graphics, PdfPTable table, RegainEssenceContent content) {
    List<RecoveryRow> recoveryRows = content.getRecoveryRows();
    for (int index = 0; index < content.getNumberOfContentLines(); index++) {
      addRecoveryCells(graphics, table, recoveryRows.get(index));
    }
  }

  private void addRecoveryCells(SheetGraphics graphics, PdfPTable table, RecoveryRow recoveryRow) {
    Font font = getDefaultFont(graphics);
    String label = recoveryRow.getLabel();
    String atEase = recoveryRow.getAtEase() != null ? String.valueOf(recoveryRow.getAtEase()) : " ";
    String relaxed = recoveryRow.getRelaxed() != null ? String.valueOf(recoveryRow.getRelaxed()) : " ";
    table.addCell(markCell(recoveryRow, new TableCell(new Phrase(label, font), INTERNAL_BORDER, ALIGN_RIGHT, ALIGN_MIDDLE)));
    table.addCell(markCell(recoveryRow, new TableCell(new Phrase(atEase, font), INTERNAL_BORDER, ALIGN_CENTER, ALIGN_MIDDLE)));
    table.addCell(markCell(recoveryRow, new TableCell(new Phrase(relaxed, font), INTERNAL_BORDER, ALIGN_CENTER, ALIGN_MIDDLE)));
  }

  private TableCell markCell(RecoveryRow recoveryRow, TableCell cell) {
    if (recoveryRow.isMarked()) {
      cell.setBorderWidth(cell.getBorderWidthTop() * 2f);
    }
    return cell;
  }

  protected final PdfPCell createHeaderCell(Font font, String text, int columnSpan) {
    PdfPCell cell = new TableCell(new Phrase(text, font), HEADER_BORDER, ALIGN_CENTER, Element.ALIGN_BOTTOM);
    cell.setColspan(columnSpan);
    return cell;
  }

  private PdfPCell createSpaceCell(SheetGraphics graphics) {
    PdfPCell spaceCell = new PdfPCell(new Phrase(" ", getDefaultFont(graphics))); //$NON-NLS-1$
    spaceCell.setBorder(HEADER_BORDER);
    return spaceCell;
  }

  private final Font getDefaultFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  public boolean hasContent(RegainEssenceContent content) {
    return content.hasContent();
  }
}
