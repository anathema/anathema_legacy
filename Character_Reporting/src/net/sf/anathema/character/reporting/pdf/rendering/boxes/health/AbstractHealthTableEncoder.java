package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Color;

public abstract class AbstractHealthTableEncoder implements ITableEncoder<ReportContent> {
  private static final int HEALTH_COLUMN_COUNT = 15;
  private static float PADDING = 0.3f;
  private static final Float[] HEALTH_LEVEL_COLUMNS = new Float[] { 0.7f, 0.6f, PADDING };
  private final IResources resources;

  public AbstractHealthTableEncoder(IResources resources) {
    this.resources = resources;
  }

  public final float encodeTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    PdfPTable table = createTable(graphics, content);
    table.setWidthPercentage(100);
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  protected IGenericTraitCollection getTraits(IGenericCharacter character) {
    return character.getTraitCollection();
  }

  private int getRowCount(HealthLevelType type) {
    if (type == HealthLevelType.TWO || type == HealthLevelType.ONE) {
      return 2;
    }
    return 1;
  }

  private final PdfPTable createTable(SheetGraphics graphics, ReportContent content) throws DocumentException {
    try {
      PdfContentByte directContent = graphics.getDirectContent();
      Image activeTemplate = Image.getInstance(HealthTemplateFactory.createRectTemplate(directContent, Color.BLACK));
      Image passiveTemplate = Image.getInstance(HealthTemplateFactory.createRectTemplate(directContent, Color.LIGHT_GRAY));
      float[] columnWidth = createColumnWidth();
      PdfPTable table = new PdfPTable(columnWidth);
      addHeaders(graphics, table);
      for (HealthLevelType type : HealthLevelType.values()) {
        addHealthTypeRows(graphics, table, content.getCharacter(), activeTemplate, passiveTemplate, type);
      }
      return table;
    } catch (BadElementException e) {
      throw new DocumentException(e);
    }
  }

  private void addHealthTypeRows(SheetGraphics graphics, PdfPTable table, IGenericCharacter character, Image activeTemplate, Image passiveTemplate,
    HealthLevelType type) {
    int rowCount = getRowCount(type);
    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      if (rowIndex == 0) {
        addHealthTypeCells(graphics, table, type, character.getPainTolerance());
      }
      else {
        addSpaceCells(graphics, table, HEALTH_LEVEL_COLUMNS.length);
      }
      addHealthCells(graphics, table, character, type, rowIndex, activeTemplate, passiveTemplate);
    }
  }

  private void addHealthTypeCells(SheetGraphics graphics, PdfPTable table, HealthLevelType type, int painTolerance) {
    addHealthPenaltyCells(graphics, table, type, painTolerance);
    addSpaceCells(graphics, table, 1);
  }

  protected abstract Phrase createIncapacitatedComment(SheetGraphics graphics);

  protected final void addSpaceCells(SheetGraphics graphics, PdfPTable table, int count) {
    for (int index = 0; index < count; index++) {
      table.addCell(createSpaceCell(graphics));
    }
  }

  protected final PdfPCell createHeaderCell(SheetGraphics graphics, String text, int columnSpan) {
    PdfPCell cell = new PdfPCell(new Phrase(text, createHeaderFont(graphics)));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(columnSpan);
    return cell;
  }

  private void addHeaders(SheetGraphics graphics, PdfPTable table) {
    table.addCell(createHeaderCell(graphics, resources.getString("Sheet.Health.Levels"), HEALTH_LEVEL_COLUMNS.length + HEALTH_COLUMN_COUNT));
    //$NON-NLS-1$
  }

  private void addHealthPenaltyCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance) {
    Font font = createDefaultFont(graphics);
    final String healthPenText = resources.getString("HealthLevelType." + level.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
    final Phrase healthPenaltyPhrase = new Phrase(healthPenText, font);
    PdfPCell healthPdfPCell = new TableCell(healthPenaltyPhrase, Rectangle.NO_BORDER);
    healthPdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    if (level == HealthLevelType.INCAPACITATED || level == HealthLevelType.DYING) {
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

  protected final int getPenalty(HealthLevelType level, int painTolerance) {
    return Math.min(0, level.getIntValue() + painTolerance);
  }

  private void addHealthCells(SheetGraphics graphics, PdfPTable table, IGenericCharacter character, HealthLevelType level, int row,
    Image activeImage,
    Image passiveImage) {
    int naturalCount = getNaturalHealthLevels(level);
    if (row < naturalCount) {
      table.addCell(createHealthCell(activeImage));
    }
    else {
      addSpaceCells(graphics, table, 1);
    }
    int additionalCount = HEALTH_COLUMN_COUNT - 1;
    for (int index = 0; index < additionalCount; index++) {
      int boxesDrawn = naturalCount + row * additionalCount + index;
      if (character.getHealthLevelTypeCount(level) > boxesDrawn) {
        table.addCell(createHealthCell(activeImage));
      }
      else {
        table.addCell(createHealthCell(passiveImage));
      }
    }
  }

  private int getNaturalHealthLevels(HealthLevelType level) {
    return level == HealthLevelType.ONE || level == HealthLevelType.TWO ? 2 : 1;
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
    Float[] objectArray = ArrayUtilities.concat(HEALTH_LEVEL_COLUMNS, healthColumns);
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(objectArray);
  }

  protected final IResources getResources() {
    return resources;
  }

  private PdfPCell createSpaceCell(SheetGraphics graphics) {
    PdfPCell spaceCell = new PdfPCell(new Phrase(" ", createDefaultFont(graphics))); //$NON-NLS-1$
    spaceCell.setBorder(Rectangle.NO_BORDER);
    return spaceCell;
  }

  private Font createCommentFont(SheetGraphics graphics) {
    return graphics.createCommentFont();
  }

  private Font createHeaderFont(SheetGraphics graphics) {
    return TableEncodingUtilities.createHeaderFont(graphics.getBaseFont());
  }

  private Font createDefaultFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
