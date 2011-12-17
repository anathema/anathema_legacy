package net.sf.anathema.character.reporting.sheet.common.movement;

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
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.elements.TableCell;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.extended.util.TableEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public abstract class AbstractHealthAndMovemenTableEncoder implements IPdfTableEncoder {
  public static final int HEALTH_RECT_SIZE = 6;
  private static final int HEALTH_COLUMN_COUNT = 10;
  protected static float PADDING = 0.3f;
  private static final Float[] HEALTH_LEVEL_COLUMNS = new Float[]{PADDING, 0.6f, 0.7f, PADDING};

  private final IResources resources;
  private final Font font;
  private Font headerFont;
  private Font commentFont;
  private PdfPCell spaceCell;

  public AbstractHealthAndMovemenTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.spaceCell = new PdfPCell(new Phrase(" ", font)); //$NON-NLS-1$
    this.spaceCell.setBorder(Rectangle.NO_BORDER);
  }

  protected abstract Float[] getMovementColumns();

  public final float encodeTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(directContent, character);
    table.setWidthPercentage(100);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
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

  protected final PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character) throws DocumentException {
    try {
      Image activeTemplate = Image.getInstance(createRectTemplate(directContent, Color.BLACK));
      Image passiveTemplate = Image.getInstance(createRectTemplate(directContent, Color.LIGHT_GRAY));
      float[] columnWidth = createColumnWidth();
      PdfPTable table = new PdfPTable(columnWidth);
      addHeaders(table);
      for (HealthLevelType type : HealthLevelType.values()) {
        addHealthTypeRows(table, character, activeTemplate, passiveTemplate, type);
      }
      return table;
    }
    catch (BadElementException e) {
      throw new DocumentException(e);
    }
  }

  private void addHealthTypeRows(PdfPTable table, IGenericCharacter character, Image activeTemplate, Image passiveTemplate, HealthLevelType type) {
    if (type == HealthLevelType.DYING) {
      return;
    }

    int rowCount = getRowCount(type);
    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      if (rowIndex == 0) {
        int painTolerance = character.getPainTolerance();
        if (type == HealthLevelType.INCAPACITATED) {
          addIncapacitatedMovement(table);
        }
        else {
          addMovementCells(table, type, painTolerance, getTraits(character));
        }
        addHealthTypeCells(table, type, painTolerance);
      }
      else {
        addSpaceCells(table, getMovementColumns().length + HEALTH_LEVEL_COLUMNS.length);
      }
      addHealthCells(table, character, type, rowIndex, activeTemplate, passiveTemplate);
    }
  }

  private void addHealthTypeCells(PdfPTable table, HealthLevelType type, int painTolerance) {
    addSpaceCells(table, 1);
    addHealthPenaltyCells(table, type, painTolerance);
    addSpaceCells(table, 1);
  }

  private void addIncapacitatedMovement(PdfPTable table) {
    final Phrase commentPhrase = createIncapacitatedComment();
    final TableCell cell = new TableCell(commentPhrase, Rectangle.NO_BORDER);
    cell.setColspan(getMovementColumns().length);
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

  private void addHeaders(PdfPTable table) {
    addMovementHeader(table);
    table.addCell(createHeaderCell(resources.getString("Sheet.Health.Levels"), 13)); //$NON-NLS-1$
  }

  protected abstract void addMovementHeader(PdfPTable table);

  public static PdfTemplate createRectTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate activeHealthRect = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    activeHealthRect.setLineWidth(1f);
    activeHealthRect.setColorStroke(strokeColor);
    activeHealthRect.rectangle(0, 0, HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    activeHealthRect.stroke();
    return activeHealthRect;
  }

  public static PdfTemplate createBashingTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate bashingSlash = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    bashingSlash.setLineWidth(1f);
    bashingSlash.setColorStroke(strokeColor);
    bashingSlash.moveTo(0, 0);
    bashingSlash.lineTo(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    bashingSlash.stroke();
    return bashingSlash;
  }

  public static PdfTemplate createLethalTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate lethalCross = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    lethalCross.addTemplate(createBashingTemplate(directContent, strokeColor), 0, 0);
    lethalCross.setLineWidth(1f);
    lethalCross.setColorStroke(strokeColor);
    lethalCross.moveTo(0, HEALTH_RECT_SIZE);
    lethalCross.lineTo(HEALTH_RECT_SIZE, 0);
    lethalCross.stroke();
    return lethalCross;
  }

  public static PdfTemplate createAggravatedTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate aggravatedStar = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    aggravatedStar.addTemplate(createLethalTemplate(directContent, strokeColor), 0, 0);
    aggravatedStar.setLineWidth(1f);
    aggravatedStar.setColorStroke(strokeColor);
    aggravatedStar.moveTo(HEALTH_RECT_SIZE / 2f, 0);
    aggravatedStar.lineTo(HEALTH_RECT_SIZE / 2f, HEALTH_RECT_SIZE);
    aggravatedStar.stroke();
    return aggravatedStar;
  }

  protected abstract void addMovementCells(PdfPTable table, HealthLevelType level, int painTolerance, IGenericTraitCollection collection);

  protected final PdfPCell createMovementCell(int value, int minValue) {
    return TableEncodingUtilities.createContentCellTable(Color.BLACK, String.valueOf(Math.max(value, minValue)), font, 0.5f, Rectangle.BOX,
                                                         Element.ALIGN_CENTER);
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

  protected final int getPenalty(HealthLevelType level, int painTolerance) {
    return Math.min(0, level.getIntValue() + painTolerance);
  }

  private void addHealthCells(PdfPTable table, IGenericCharacter character, HealthLevelType level, int row, Image activeImage, Image passiveImage) {
    int naturalCount = getNaturalHealthLevels(level);
    if (row < naturalCount) {
      table.addCell(createHealthCell(activeImage));
    }
    else {
      addSpaceCells(table, 1);
    }
    int additionalCount = 9;
    if (level == HealthLevelType.FOUR) {
      addSpaceCells(table, 1);
      TableCell cell = new TableCell(new Phrase(resources.getString("HealthLevelType.Dying.Short"), commentFont), Rectangle.BOTTOM); //$NON-NLS-1$
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
      cell.setColspan(additionalCount - 1);
      table.addCell(cell);
      return;
    }
    if (level == HealthLevelType.INCAPACITATED) {
      addSpaceCells(table, 1);
      for (int index = 0; index < additionalCount - 1; index++) {
        if (index < character.getHealthLevelTypeCount(HealthLevelType.DYING)) {
          table.addCell(createHealthCell(activeImage));
        }
        else {
          table.addCell(createHealthCell(passiveImage));
        }
      }
      return;
    }
    for (int index = 0; index < additionalCount; index++) {
      int value = naturalCount + row * additionalCount + index + 1;
      if (value <= character.getHealthLevelTypeCount(level)) {
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
    Float[] movementAndLevelColumns = ArrayUtilities.concat(getMovementColumns(), HEALTH_LEVEL_COLUMNS);
    Float[] healthColumns = TableEncodingUtilities.createStandardColumnWidths(HEALTH_COLUMN_COUNT, 0.4f);
    Float[] objectArray = ArrayUtilities.concat(movementAndLevelColumns, healthColumns);
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(objectArray);
  }

  protected final Font getCommentFont() {
    return commentFont;
  }

  protected final IResources getResources() {
    return resources;
  }
}
