package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public abstract class AbstractMovementTableEncoder implements ITableEncoder<ReportContent> {
  protected static float PADDING = 0.3f;

  private final IResources resources;
  private final Font font;
  private Font headerFont;
  private Font commentFont;
  private PdfPCell spaceCell;

  public AbstractMovementTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createTableFont(baseFont);
    this.headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.spaceCell = new PdfPCell(new Phrase(" ", font)); //$NON-NLS-1$
    this.spaceCell.setBorder(Rectangle.NO_BORDER);
  }

  protected abstract Float[] getMovementColumns();

  public final float encodeTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    PdfPTable table = createTable(content);
    table.setWidthPercentage(100);
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  protected IGenericTraitCollection getTraits(IGenericCharacter character) {
    return character.getTraitCollection();
  }

  protected final PdfPTable createTable(ReportContent content) throws DocumentException {
    float[] columnWidth = createColumnWidth();
    PdfPTable table = new PdfPTable(columnWidth);
    addMovementHeader(table);
    for (HealthLevelType type : HealthLevelType.values()) {
      addHealthTypeRows(table, content.getCharacter(), type);
    }
    return table;
  }

  private void addHealthTypeRows(PdfPTable table, IGenericCharacter character, HealthLevelType type) {
    if (type == HealthLevelType.DYING) {
      return;
    }

    int painTolerance = character.getPainTolerance();
    if (type == HealthLevelType.INCAPACITATED) {
      addIncapacitatedMovement(table);
    } else {
      addMovementCells(table, type, painTolerance, getTraits(character));
    }
  }

  protected void addHealthPenaltyCells(PdfPTable table, HealthLevelType level, int painTolerance) {
    final String healthPenText = resources.getString("HealthLevelType." + level.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
    final Phrase healthPenaltyPhrase = new Phrase(healthPenText, font);
    PdfPCell healthPdfPCell = new TableCell(healthPenaltyPhrase, Rectangle.NO_BORDER);
    healthPdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    healthPdfPCell.setVerticalAlignment(Element.ALIGN_CENTER);
    if (level == HealthLevelType.INCAPACITATED || level == HealthLevelType.DYING) {
      healthPdfPCell.setColspan(2);
      table.addCell(healthPdfPCell);
    } else {
      table.addCell(healthPdfPCell);
      String painToleranceText = " "; //$NON-NLS-1$
      if (painTolerance > 0) {
        final int value = getPenalty(level, painTolerance);
        painToleranceText = "(" + (value == 0 ? "-" : "") + value + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      }
      final TableCell painToleranceCell = new TableCell(new Phrase(painToleranceText, font), Rectangle.NO_BORDER);
      painToleranceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      painToleranceCell.setVerticalAlignment(Element.ALIGN_CENTER);
      table.addCell(painToleranceCell);
    }
  }

  private void addIncapacitatedMovement(PdfPTable table) {
    final Phrase commentPhrase = createIncapacitatedComment();
    final TableCell cell = new TableCell(commentPhrase, Rectangle.NO_BORDER);
    cell.setColspan(getMovementColumns().length);
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    table.addCell(cell);
  }

  protected abstract Phrase createIncapacitatedComment();

  protected final void addSpaceCells(PdfPTable table, int count) {
    for (int index = 0; index < count; index++) {
      table.addCell(spaceCell);
    }
  }

  protected final PdfPCell createHeaderCell(String text, int columnSpan) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(columnSpan);
    return cell;
  }

  protected abstract void addMovementHeader(PdfPTable table);

  protected abstract void addMovementCells(PdfPTable table, HealthLevelType level, int painTolerance, IGenericTraitCollection collection);

  protected final PdfPCell createMovementCell(int value, int minValue) {
    return TableEncodingUtilities.createContentCellTable(Color.BLACK, String.valueOf(Math.max(value, minValue)), font, 0.5f, Rectangle.BOX, Element.ALIGN_CENTER);
  }

  protected final int getPenalty(HealthLevelType level, int painTolerance) {
    return Math.min(0, level.getIntValue() + painTolerance);
  }

  private float[] createColumnWidth() {
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(getMovementColumns());
  }

  protected final Font getCommentFont() {
    return commentFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
