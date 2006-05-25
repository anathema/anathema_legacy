package net.sf.anathema.character.reporting.sheet.second;

import java.awt.Color;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
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
  private final IResources resources;
  private final BaseFont baseFont;
  private final Font font;

  public SecondEditionHealthAndMovemenTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.font = new Font(baseFont, IVoidStateFormatConstants.FONT_SIZE, Font.NORMAL, Color.BLACK);
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
      Image passiveTemplate = Image.getInstance(createRectTemplate(directContent, healthRectSize, Color.GRAY));
      float[] columnWidth = createColumnWidth();
      PdfPTable table = new PdfPTable(columnWidth);
      addMovementCells(table, character, HealthLevelType.ZERO);
      addHealthCells(table, character, HealthLevelType.ZERO, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.ONE);
      addHealthCells(table, character, HealthLevelType.ONE, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.ONE);
      addHealthCells(table, character, HealthLevelType.ONE, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.TWO);
      addHealthCells(table, character, HealthLevelType.TWO, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.TWO);
      addHealthCells(table, character, HealthLevelType.TWO, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.FOUR);
      addHealthCells(table, character, HealthLevelType.FOUR, activeTemplate, passiveTemplate);
      addMovementCells(table, character, HealthLevelType.INCAPACITATED);
      addHealthCells(table, character, HealthLevelType.INCAPACITATED, activeTemplate, passiveTemplate);
      return table;
    }
    catch (BadElementException e) {
      throw new DocumentException(e);
    }
  }

  private PdfTemplate createRectTemplate(PdfContentByte directContent, final int healthRectSize, final Color strokeColor) {
    PdfTemplate activeHealthRect = directContent.createTemplate(healthRectSize, healthRectSize);
    activeHealthRect.setLineWidth(0.75f);
    activeHealthRect.setColorStroke(strokeColor);
    activeHealthRect.rectangle(0, 0, healthRectSize, healthRectSize);
    activeHealthRect.stroke();
    return activeHealthRect;
  }

  private void addMovementCells(PdfPTable table, IGenericCharacter character, HealthLevelType level) {
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
    table.addCell(new Phrase(" ", font));
  }

  private void addHealthCells(
      PdfPTable table,
      IGenericCharacter character,
      HealthLevelType level,
      Image activeImage,
      Image passiveImage) {
    int passiveCellCount = 8;
    for (int index = 0; index < passiveCellCount; index++) {
      table.addCell(createHealthCell(passiveImage));
    }
    for (int index = passiveCellCount; index < HEALTH_COLUMN_COUNT; index++) {
      table.addCell(createHealthCell(activeImage));
    }
  }

  private PdfPCell createHealthCell(Image image) {
    PdfPCell cell = new PdfPCell(image, false);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private float[] createColumnWidth() {
    float padding = 0.3f;
    Float[] movementColumns = new Float[] { 1f, padding, 1f, padding, 1f, 1f, padding, 1f, 1f, padding };
    Float[] healthColumns = TableEncodingUtilities.createStandardColumnWidths(HEALTH_COLUMN_COUNT, 0.4f);
    Float[] objectArray = ArrayUtilities.concat(movementColumns, healthColumns);
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(objectArray);
  }
}