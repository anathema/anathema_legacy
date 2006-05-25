package net.sf.anathema.character.reporting.sheet.second;

import java.awt.Color;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableCell;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;

public class SecondEditionHealthAndMovemenTableEncoder implements IPdfTableEncoder {

  private static final int HEALTH_COLUMN_COUNT = 10;
  private static float PADDING = 0.3f;
  private static final Float[] MOVEMENT_COLUMNS = new Float[] {
      1f,
      PADDING,
      1f,
      PADDING,
      1f,
      1f,
      PADDING,
      0.6f,
      0.7f,
      PADDING };
  private final IResources resources;
  private final Font font;
  private Font headerFont;
  private PdfPCell spaceCell;

  public SecondEditionHealthAndMovemenTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    this.spaceCell = new PdfPCell(new Phrase(" ", font)); //$NON-NLS-1$
    this.spaceCell.setBorder(Rectangle.NO_BORDER);
  }

  public void encodeTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(directContent, character);
    table.setWidthPercentage(100);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
  }

  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character) throws DocumentException {
    final int healthRectSize = 6;
    try {
      Image activeTemplate = Image.getInstance(createRectTemplate(directContent, healthRectSize, Color.BLACK));
      Image passiveTemplate = Image.getInstance(createRectTemplate(directContent, healthRectSize, Color.LIGHT_GRAY));
      float[] columnWidth = createColumnWidth();
      PdfPTable table = new PdfPTable(columnWidth);
      addHeaders(table);
      addMovementCells(table, character, HealthLevelType.ZERO);
      addHealthCells(table, character, HealthLevelType.ZERO, 0, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.ONE);
      addHealthCells(table, character, HealthLevelType.ONE, 0, activeTemplate, passiveTemplate);
      addSpaceCells(table, MOVEMENT_COLUMNS.length);
      addHealthCells(table, character, HealthLevelType.ONE, 1, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.TWO);
      addHealthCells(table, character, HealthLevelType.TWO, 0, activeTemplate, passiveTemplate);
      addSpaceCells(table, MOVEMENT_COLUMNS.length);
      addHealthCells(table, character, HealthLevelType.TWO, 1, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.FOUR);
      addHealthCells(table, character, HealthLevelType.FOUR, 0, activeTemplate, passiveTemplate);
      addSpaceCells(table, MOVEMENT_COLUMNS.length);
      addHealthCells(table, character, HealthLevelType.INCAPACITATED, 0, activeTemplate, passiveTemplate);
      return table;
    }
    catch (BadElementException e) {
      throw new DocumentException(e);
    }
  }

  private void addSpaceCells(PdfPTable table, int count) {
    for (int index = 0; index < count; index++) {
      table.addCell(spaceCell);
    }
  }

  private PdfPCell createHeaderCell(String text, int columnSpan) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(columnSpan);
    return cell;
  }

  private void addHeaders(PdfPTable table) {
    table.addCell(createHeaderCell("Move", 2));
    table.addCell(createHeaderCell("Dash", 2));
    table.addCell(createHeaderCell("Jump (H/V)", 3));
    table.addCell(createHeaderCell("Health Levels", 13));
  }

  private PdfTemplate createRectTemplate(PdfContentByte directContent, final int healthRectSize, final Color strokeColor) {
    PdfTemplate activeHealthRect = directContent.createTemplate(healthRectSize, healthRectSize);
    activeHealthRect.setLineWidth(1f);
    activeHealthRect.setColorStroke(strokeColor);
    activeHealthRect.rectangle(0, 0, healthRectSize, healthRectSize);
    activeHealthRect.stroke();
    return activeHealthRect;
  }

  private void addMovementCells(PdfPTable table, IGenericCharacter character, HealthLevelType level) {
    int painTolerance = character.getPainTolerance();
    table.addCell(new Phrase(" ", font));
    addSpaceCells(table, 1);
    table.addCell(new Phrase(" ", font));
    addSpaceCells(table, 1);
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    addSpaceCells(table, 1);
    addHealthPenaltyCells(table, level, painTolerance);
    addSpaceCells(table, 1);
  }

  private void addHealthPenaltyCells(PdfPTable table, HealthLevelType level, int painTolerance) {
    final String healthPenText = resources.getString("HealthLevelType." + level.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
    final Phrase healthPenaltyPhrase = new Phrase(healthPenText, font);
    PdfPCell healthPdfPCell = new TableCell(healthPenaltyPhrase, Rectangle.NO_BORDER);
    if (level == HealthLevelType.INCAPACITATED) {
      healthPdfPCell.setColspan(2);
      table.addCell(healthPdfPCell);
    }
    else {
      table.addCell(healthPdfPCell);
      String painToleranceText = " "; //$NON-NLS-1$
      if (painTolerance > 0) {
        final int value = getPenalty(level, painTolerance);
        painToleranceText = "(" + (value == 0 ? "-" : "") + value + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      }
      final TableCell painToleranceCell = new TableCell(new Phrase(painToleranceText, font), Rectangle.NO_BORDER);
      painToleranceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(painToleranceCell);
    }
  }

  private int getPenalty(HealthLevelType level, int painTolerance) {
    return Math.min(0, level.getIntValue() + painTolerance);
  }

  private void addHealthCells(
      PdfPTable table,
      IGenericCharacter character,
      HealthLevelType level,
      int row,
      Image activeImage,
      Image passiveImage) {
    int naturalCount = getNaturalHealthLevels(level);
    if (row < naturalCount) {
      table.addCell(createHealthCell(activeImage));
    }
    else {
      addSpaceCells(table, 1);
    }
    int additionalCount = 9;
    for (int index = 0; index < additionalCount; index++) {
      int value = naturalCount + (row * additionalCount) + index + 1;
      if (value <= character.getHealthLevelTypeCount(level)) {
        table.addCell(createHealthCell(activeImage));
      }
      else {
        table.addCell(createHealthCell(passiveImage));
      }
    }
  }

  private int getNaturalHealthLevels(HealthLevelType level) {
    return (level == HealthLevelType.ONE || level == HealthLevelType.TWO) ? 2 : 1;
  }

  private PdfPCell createHealthCell(Image image) {
    PdfPCell cell = new PdfPCell(image, false);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private float[] createColumnWidth() {
    Float[] healthColumns = TableEncodingUtilities.createStandardColumnWidths(HEALTH_COLUMN_COUNT, 0.4f);
    Float[] objectArray = ArrayUtilities.concat(MOVEMENT_COLUMNS, healthColumns);
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(objectArray);
  }
}