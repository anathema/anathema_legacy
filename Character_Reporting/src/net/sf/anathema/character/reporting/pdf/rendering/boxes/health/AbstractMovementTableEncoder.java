package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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

import static com.itextpdf.text.Element.*;
import static com.itextpdf.text.Rectangle.BOX;
import static com.itextpdf.text.Rectangle.NO_BORDER;

public abstract class AbstractMovementTableEncoder implements ITableEncoder<ReportContent> {
  protected static float PADDING = 0.3f;

  private final IResources resources;

  public AbstractMovementTableEncoder(IResources resources) {
    this.resources = resources;
  }

  protected abstract Float[] getMovementColumns();

  public final float encodeTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    PdfPTable table = createTable(graphics, content);
    table.setWidthPercentage(100);
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  protected IGenericTraitCollection getTraits(IGenericCharacter character) {
    return character.getTraitCollection();
  }

  protected final PdfPTable createTable(SheetGraphics graphics, ReportContent content) {
    float[] columnWidth = createColumnWidth();
    PdfPTable table = new PdfPTable(columnWidth);
    addMovementHeader(graphics, table);
    for (HealthLevelType type : HealthLevelType.values()) {
      addHealthTypeRows(graphics, table, content.getCharacter(), type);
    }
    return table;
  }

  private void addHealthTypeRows(SheetGraphics graphics, PdfPTable table, IGenericCharacter character, HealthLevelType type) {
    if (type == HealthLevelType.DYING) {
      return;
    }

    int painTolerance = character.getPainTolerance();
    if (type == HealthLevelType.INCAPACITATED) {
      addIncapacitatedMovement(graphics, table);
    } else {
      addMovementCells(graphics, table, type, painTolerance, getTraits(character));
    }
  }

  protected void addHealthPenaltyCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance) {
    String healthPenText = resources.getString("HealthLevelType." + level.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
    Phrase healthPenaltyPhrase = new Phrase(healthPenText, createFont(graphics));
    PdfPCell healthPdfPCell = new TableCell(healthPenaltyPhrase, NO_BORDER);
    healthPdfPCell.setHorizontalAlignment(ALIGN_RIGHT);
    healthPdfPCell.setVerticalAlignment(ALIGN_CENTER);
    if (level == HealthLevelType.INCAPACITATED || level == HealthLevelType.DYING) {
      healthPdfPCell.setColspan(2);
      table.addCell(healthPdfPCell);
    } else {
      table.addCell(healthPdfPCell);
      String painToleranceText = " "; //$NON-NLS-1$
      if (painTolerance > 0) {
        int value = getPenalty(level, painTolerance);
        painToleranceText = "(" + (value == 0 ? "-" : "") + value + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      }
      TableCell painToleranceCell = new TableCell(new Phrase(painToleranceText, createFont(graphics)), NO_BORDER);
      painToleranceCell.setHorizontalAlignment(ALIGN_LEFT);
      painToleranceCell.setVerticalAlignment(ALIGN_CENTER);
      table.addCell(painToleranceCell);
    }
  }

  private void addIncapacitatedMovement(SheetGraphics graphics, PdfPTable table) {
    Phrase commentPhrase = createIncapacitatedComment(graphics);
    TableCell cell = new TableCell(commentPhrase, NO_BORDER);
    cell.setColspan(getMovementColumns().length);
    cell.setHorizontalAlignment(ALIGN_RIGHT);
    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    table.addCell(cell);
  }

  protected abstract Phrase createIncapacitatedComment(SheetGraphics graphics);

  protected final void addSpaceCells(SheetGraphics graphics, PdfPTable table, int count) {
    PdfPCell spaceCell = new PdfPCell(new Phrase(" ", createFont(graphics))); //$NON-NLS-1$
    spaceCell.setBorder(NO_BORDER);
    for (int index = 0; index < count; index++) {
      table.addCell(spaceCell);
    }
  }

  private Font createFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  protected final PdfPCell createHeaderCell(SheetGraphics graphics, String text, int columnSpan) {
    PdfPCell cell = new PdfPCell(new Phrase(text, getHeaderFont(graphics)));
    cell.setBorder(NO_BORDER);
    cell.setColspan(columnSpan);
    return cell;
  }

  protected abstract void addMovementHeader(SheetGraphics graphics, PdfPTable table);

  protected abstract void addMovementCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance, IGenericTraitCollection collection);

  protected final PdfPCell createMovementCell(SheetGraphics graphics, int value, int minValue) {
    Font font = createFont(graphics);
    String valueText = String.valueOf(Math.max(value, minValue));
    return TableEncodingUtilities.createContentCellTable(BaseColor.BLACK, valueText, font, 0.5f, BOX, ALIGN_CENTER);
  }

  protected final int getPenalty(HealthLevelType level, int painTolerance) {
    return Math.min(0, level.getIntValue() + painTolerance);
  }

  private float[] createColumnWidth() {
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(getMovementColumns());
  }

  protected final Font getCommentFont(SheetGraphics graphics) {
    return graphics.createCommentFont();
  }

  private Font getHeaderFont(SheetGraphics graphics) {
    return graphics.createCommentFont();
  }

  protected final IResources getResources() {
    return resources;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
