package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

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
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IdentifiedInteger;

import java.awt.*;

public class EssenceTableEncoder implements IPdfTableEncoder {
  protected static float PADDING = 1f;
  protected static float DOTS_WIDTH = 130f;

  private final IResources resources;
  private final int essenceMax;
  private final Font font;
  private Font boldFont;
  private Font commentFont;
  private PdfPCell spaceCell;
  private PdfPCell internalSpaceCell;
  private PdfTraitEncoder traitEncoder;
  private String[] specialRecoveryRows;

  private static final int HEADER_BORDER = Rectangle.NO_BORDER;
  private static final int INTERNAL_BORDER = Rectangle.BOX;
  private static final int LABEL_BORDER = Rectangle.BOX;

  public EssenceTableEncoder(IResources resources, BaseFont baseFont, int essenceMax, String... specialRecoveryRows) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.boldFont = TableEncodingUtilities.createBoldFont(baseFont);
    this.spaceCell = new PdfPCell(new Phrase(" ", font)); //$NON-NLS-1$
    this.spaceCell.setBorder(HEADER_BORDER);
    this.internalSpaceCell = new PdfPCell(new Phrase(" ", font));
    this.internalSpaceCell.setBorder(INTERNAL_BORDER);
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
    this.essenceMax = essenceMax;
    this.specialRecoveryRows = specialRecoveryRows;
  }

  protected Float[] getEssenceColumns() {
    return new Float[]{8f, 2f, 3f, (10f / 3f), PADDING, 6f, 2.5f, 2.5f};
  }

  private float[] createColumnWidth() {
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(getEssenceColumns());
  }

  public final float encodeTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    return encodeTable(directContent, character, bounds, false);
  }

  public final float getTableHeight(IGenericCharacter character, float width) throws DocumentException {
    int pools = 0;
    if (character.getPersonalPoolValue() > 0) {
      pools++;
    }
    if (character.getPeripheralPoolValue() > 0) {
      pools++;
    }
    if (character.getOverdrivePoolValue() > 0) {
      pools++;
    }
    for (IdentifiedInteger pool : character.getComplexPools()) {
      if (pool.getValue() > 0) {
        pools++;
      }
    }

    int lines = 1 + Math.max(pools, 5 + specialRecoveryRows.length);
    return traitEncoder.getTraitHeight() + lines * IVoidStateFormatConstants.BARE_LINE_HEIGHT + 1.75f * IVoidStateFormatConstants.TEXT_PADDING;
  }

  protected final float encodeTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds,
                                    boolean simulate) throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(directContent, character);
    table.setWidthPercentage(100);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go(simulate);
    return table.getTotalHeight();
  }

  protected IGenericTraitCollection getTraits(IGenericCharacter character) {
    return character.getTraitCollection();
  }

  protected final PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character) throws DocumentException {
    float[] columnWidth = createColumnWidth();
    PdfPTable table = new PdfPTable(columnWidth);
    addEssenceHeader(table, createDots(directContent, character, DOTS_WIDTH));
    addEssencePoolRows(table, character);
    return table;
  }

  protected final Image createDots(PdfContentByte directContent, IGenericCharacter character, float width) throws BadElementException {
    PdfTemplate dotsTemplate = directContent.createTemplate(width, traitEncoder.getTraitHeight());
    int value = character.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    traitEncoder.encodeDotsCenteredAndUngrouped(dotsTemplate, new Position(0, PdfTraitEncoder.DOT_PADDING), width, value, essenceMax);
    return Image.getInstance(dotsTemplate);
  }

  protected final void addEssenceHeader(PdfPTable table, Image firstCell) {
    PdfPCell headerCell = new TableCell(firstCell);
    headerCell.setColspan(4);
    table.addCell(headerCell);
    table.addCell(spaceCell);
    table.addCell(createBigHeaderCell(getResources().getString("Sheet.Essence.Regaining"), 3)); //$NON-NLS-1$

    table.addCell(spaceCell);
    table.addCell(createHeaderCell(getResources().getString("Sheet.Essence.Total"), 1)); //$NON-NLS-1$
    table.addCell(createHeaderCell(getResources().getString("Sheet.Essence.Committed"), 1)); //$NON-NLS-1$
    table.addCell(createHeaderCell(getResources().getString("Sheet.Essence.Available"), 1)); //$NON-NLS-1$
    table.addCell(spaceCell);
    table.addCell(spaceCell);
    table.addCell(createHeaderCell(getResources().getString("Sheet.Essence.AtEase"), 1)); //$NON-NLS-1$
    table.addCell(createHeaderCell(getResources().getString("Sheet.Essence.Relaxed"), 1)); //$NON-NLS-1$
  }

  private void addEssencePoolRows(PdfPTable table, IGenericCharacter character) {
    int personalPool = character.getPersonalPoolValue();
    int peripheralPool = character.getPeripheralPoolValue();
    int overdrivePool = character.getOverdrivePoolValue();
    IdentifiedInteger[] complexPools = character.getComplexPools();

    int committed = character.getAttunedPoolValue();
    int peripheralCommitted = Math.min(peripheralPool, committed);
    int personalCommitted = committed - peripheralCommitted;

    int row = 1;
    if (personalPool > 0) {
      String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
      addPoolRow(table, personalLabel, personalPool, personalCommitted, row++);
    }

    if (peripheralPool > 0) {
      String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
      addPoolRow(table, peripheralLabel, peripheralPool, peripheralCommitted, row++);
    }

    if (overdrivePool > 0) {
      String overdriveLabel = resources.getString("Sheet.Essence.OverdrivePool"); //$NON-NLS-1$
      addPoolRow(table, overdriveLabel, overdrivePool, null, row++);
    }

    for (IdentifiedInteger complexPool : complexPools) {
      String poolId = complexPool.getId();
      int poolValue = complexPool.getValue();
      if (poolValue > 0) {
        String poolLabel = resources.getString("Sheet.Essence." + poolId); //$NON-NLS-1$
        addPoolRow(table, poolLabel, poolValue, null, row++);
      }
    }

    while (row <= 5 + specialRecoveryRows.length) {
      addPoolRow(table, null, 0, null, row++);
    }
  }

  private void addPoolRow(PdfPTable table, String poolLabel, int poolCapacity, Integer poolCommitted, int rowNumber) {
    if (poolLabel != null) {
      int poolAvailable = poolCapacity;
      if (poolCommitted != null) {
        poolAvailable -= poolCommitted;
      }

      PdfPCell labelCell = new TableCell(new Phrase(poolLabel, font), LABEL_BORDER, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
      table.addCell(labelCell);

      PdfPCell totalCell = new TableCell(new Phrase(Integer.toString(poolCapacity) + " m", font), INTERNAL_BORDER, //$NON-NLS-1$
                                         Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
      table.addCell(totalCell);

      PdfPCell committedCell;
      if (poolCommitted != null) {
        committedCell = new TableCell(new Phrase(poolCommitted.toString() + " m", font), INTERNAL_BORDER, //$NON-NLS-1$
                                      Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
      }
      else {
        committedCell = new TableCell(new Phrase(" ", font), INTERNAL_BORDER); //$NON-NLS-1$
        committedCell.setBackgroundColor(Color.LIGHT_GRAY);
      }
      table.addCell(committedCell);

      PdfPCell availableCell = new TableCell(new Phrase("/ " + Integer.toString(poolAvailable) + " m", font), INTERNAL_BORDER,
      //$NON-NLS-1$ //$NON-NLS-2$
                                             Element.ALIGN_RIGHT, Element.ALIGN_MIDDLE);
      table.addCell(availableCell);
    }
    else {
      table.addCell(spaceCell);
      table.addCell(spaceCell);
      table.addCell(spaceCell);
      table.addCell(spaceCell);
    }

    table.addCell(spaceCell);
    if (rowNumber == 1) {
      String label = getResources().getString("Sheet.Essence.NaturalRecovery"); //$NON-NLS-1$
      table.addCell(new TableCell(new Phrase(label, font), INTERNAL_BORDER, Element.ALIGN_RIGHT, Element.ALIGN_MIDDLE));
      table.addCell(new TableCell(new Phrase(Integer.valueOf(4).toString(), font), INTERNAL_BORDER, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE));
      table.addCell(new TableCell(new Phrase(Integer.valueOf(8).toString(), font), INTERNAL_BORDER, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE));
    }
    else if (rowNumber - 6 >= specialRecoveryRows.length) {
      table.addCell(spaceCell);
      table.addCell(spaceCell);
      table.addCell(spaceCell);
    }
    else {
      String label;
      switch (rowNumber) {
        case 2:
          label = getResources().getString("Sheet.Essence.Hearthstones"); //$NON-NLS-1$
          break;
        case 3:
          label = getResources().getString("Sheet.Essence.Cult"); //$NON-NLS-1$
          break;
        case 4:
          label = ""; //$NON-NLS-1$
          break;
        case 5:
          label = getResources().getString("Sheet.Essence.TotalPerHour"); //$NON-NLS-1$
          break;
        default:
          label = getResources().getString(specialRecoveryRows[rowNumber - 6]);
          break;
      }
      PdfPCell cell = new TableCell(new Phrase(label, font), INTERNAL_BORDER, Element.ALIGN_RIGHT, Element.ALIGN_MIDDLE);
      PdfPCell rowSpaceCell = internalSpaceCell;
      if (rowNumber >= 5) {
        cell.setBorderWidth(2f * cell.getBorderWidthTop());

        rowSpaceCell = new PdfPCell(rowSpaceCell);
        rowSpaceCell.setBorderWidth(2f * rowSpaceCell.getBorderWidthTop());
      }
      table.addCell(cell);
      table.addCell(rowSpaceCell);
      table.addCell(rowSpaceCell);
    }
  }

  protected final PdfPCell createHeaderCell(String text, int columnSpan) {
    PdfPCell cell = new TableCell(new Phrase(text, font), HEADER_BORDER, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM);
    cell.setColspan(columnSpan);
    return cell;
  }

  protected final PdfPCell createBigHeaderCell(String text, int columnSpan) {
    PdfPCell cell = new TableCell(new Phrase(text, boldFont), HEADER_BORDER, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
    cell.setColspan(columnSpan);
    return cell;
  }

  protected final Font getCommentFont() {
    return commentFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
