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

public abstract class AbstractHealthAndMovementTableEncoder implements ITableEncoder<ReportContent> {
  public static final int HEALTH_RECT_SIZE = 6;
  private static final int HEALTH_COLUMN_COUNT = 10;
  protected static float PADDING = 0.3f;
  private static final Float[] HEALTH_LEVEL_COLUMNS = new Float[]{PADDING, 0.6f, 0.7f, PADDING};

  private final IResources resources;

  public AbstractHealthAndMovementTableEncoder(IResources resources) {
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

  private int getRowCount(HealthLevelType type) {
    if (type == HealthLevelType.TWO || type == HealthLevelType.ONE) {
      return 2;
    }
    return 1;
  }

  protected final PdfPTable createTable(SheetGraphics graphics, ReportContent content) throws DocumentException {
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
    if (type == HealthLevelType.DYING) {
      return;
    }

    int rowCount = getRowCount(type);
    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      if (rowIndex == 0) {
        int painTolerance = character.getPainTolerance();
        if (type == HealthLevelType.INCAPACITATED) {
          addIncapacitatedMovement(graphics, table);
        } else {
          addMovementCells(graphics, table, type, painTolerance, getTraits(character));
        }
        addHealthTypeCells(graphics, table, type, painTolerance);
      } else {
        addSpaceCells(graphics, table, getMovementColumns().length + HEALTH_LEVEL_COLUMNS.length);
      }
      addHealthCells(graphics, table, character, type, rowIndex, activeTemplate, passiveTemplate);
    }
  }

  private void addHealthTypeCells(SheetGraphics graphics, PdfPTable table, HealthLevelType type, int painTolerance) {
    addSpaceCells(graphics, table, 1);
    addHealthPenaltyCells(graphics, table, type, painTolerance);
    addSpaceCells(graphics, table, 1);
  }

  private void addIncapacitatedMovement(SheetGraphics graphics, PdfPTable table) {
    final Phrase commentPhrase = createIncapacitatedComment(graphics);
    final TableCell cell = new TableCell(commentPhrase, Rectangle.NO_BORDER);
    cell.setColspan(getMovementColumns().length);
    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    table.addCell(cell);
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
    addMovementHeader(graphics, table);
    table.addCell(createHeaderCell(graphics, resources.getString("Sheet.Health.Levels"), 13)); //$NON-NLS-1$
  }

  protected abstract void addMovementHeader(SheetGraphics graphics, PdfPTable table);

  protected abstract void addMovementCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance,
                                           IGenericTraitCollection collection);

  protected final PdfPCell createMovementCell(SheetGraphics graphics, int value, int minValue) {
    Font font = createDefaultFont(graphics);
    return TableEncodingUtilities
            .createContentCellTable(Color.BLACK, String.valueOf(Math.max(value, minValue)), font, 0.5f, Rectangle.BOX, Element.ALIGN_CENTER);
  }

  private void addHealthPenaltyCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance) {
    Font font = createDefaultFont(graphics);
    final String healthPenText = resources.getString("HealthLevelType." + level.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
    final Phrase healthPenaltyPhrase = new Phrase(healthPenText, font);
    PdfPCell healthPdfPCell = new TableCell(healthPenaltyPhrase, Rectangle.NO_BORDER);
    if (level == HealthLevelType.INCAPACITATED) {
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
    } else {
      addSpaceCells(graphics, table, 1);
    }
    int additionalCount = 9;
    if (level == HealthLevelType.FOUR) {
      addSpaceCells(graphics, table, 1);
      TableCell cell =
              new TableCell(new Phrase(resources.getString("HealthLevelType.Dying.Short"), createCommentFont(graphics)), Rectangle.BOTTOM); //$NON-NLS-1$
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
      cell.setColspan(additionalCount - 1);
      table.addCell(cell);
      return;
    }
    if (level == HealthLevelType.INCAPACITATED) {
      addSpaceCells(graphics, table, 1);
      for (int index = 0; index < additionalCount - 1; index++) {
        if (index < character.getHealthLevelTypeCount(HealthLevelType.DYING)) {
          table.addCell(createHealthCell(activeImage));
        } else {
          table.addCell(createHealthCell(passiveImage));
        }
      }
      return;
    }
    for (int index = 0; index < additionalCount; index++) {
      int value = naturalCount + row * additionalCount + index + 1;
      if (value <= character.getHealthLevelTypeCount(level)) {
        table.addCell(createHealthCell(activeImage));
      } else {
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
    Float[] movementAndLevelColumns = ArrayUtilities.concat(Float.class, getMovementColumns(), HEALTH_LEVEL_COLUMNS);
    Float[] healthColumns = TableEncodingUtilities.createStandardColumnWidths(HEALTH_COLUMN_COUNT, 0.4f);
    Float[] objectArray = ArrayUtilities.concat(Float.class, movementAndLevelColumns, healthColumns);
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

  protected Font createCommentFont(SheetGraphics graphics) {
    return graphics.createCommentFont();
  }

  protected Font createHeaderFont(SheetGraphics graphics) {
    return TableEncodingUtilities.createHeaderFont(graphics.getBaseFont());
  }

  protected Font createDefaultFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
