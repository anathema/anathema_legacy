package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.content.essence.pools.PoolRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.RecoveryRow;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.GraphicsTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;

import java.awt.*;
import java.util.List;

import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_MIDDLE;
import static com.lowagie.text.Element.ALIGN_RIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder.DOT_PADDING;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.BARE_LINE_HEIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class ExtendedEssenceTableEncoder implements ITableEncoder<ExtendedEssenceContent> {
  protected static float PADDING = 1f;
  protected static float DOTS_WIDTH = 130f;

  private final Font font;
  private Font boldFont;
  private Font commentFont;
  private PdfPCell spaceCell;
  private PdfPCell internalSpaceCell;
  private PdfTraitEncoder traitEncoder;

  private static final int HEADER_BORDER = Rectangle.NO_BORDER;
  private static final int INTERNAL_BORDER = Rectangle.BOX;
  private static final int LABEL_BORDER = Rectangle.BOX;
  private BaseFont baseFont;

  public ExtendedEssenceTableEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.boldFont = TableEncodingUtilities.createBoldFont(baseFont);
    this.spaceCell = new PdfPCell(new Phrase(" ", font)); //$NON-NLS-1$
    this.spaceCell.setBorder(HEADER_BORDER);
    this.internalSpaceCell = new PdfPCell(new Phrase(" ", font));
    this.internalSpaceCell.setBorder(INTERNAL_BORDER);
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder();
  }

  protected Float[] getEssenceColumns() {
    return new Float[] { 8f, 2f, 3f, (10f / 3f), PADDING, 6f, 2.5f, 2.5f };
  }

  private float[] createColumnWidth() {
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(getEssenceColumns());
  }


  public final float getTableHeight(ExtendedEssenceContent content, float width) throws DocumentException {
    int lines = content.getOverallLineCount();
    return traitEncoder.getTraitHeight() + lines * BARE_LINE_HEIGHT + 1.75f * TEXT_PADDING;
  }

  @Override
  public final float encodeTable(SheetGraphics graphics, ExtendedEssenceContent content, Bounds bounds) throws DocumentException {
    PdfPTable table = createTable(graphics, content);
    table.setWidthPercentage(100);
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  protected IGenericTraitCollection getTraits(IGenericCharacter character) {
    return character.getTraitCollection();
  }

  protected final PdfPTable createTable(SheetGraphics graphics, ExtendedEssenceContent content) throws DocumentException {
    float[] columnWidth = createColumnWidth();
    PdfPTable table = new PdfPTable(columnWidth);
    addEssenceHeader(table, createDots(graphics, content, DOTS_WIDTH), content);
    addEssencePoolRows(table, content);
    return table;
  }

  protected final Image createDots(SheetGraphics graphics, ExtendedEssenceContent content, float width) throws BadElementException {
    GraphicsTemplate template = graphics.createTemplate(width, traitEncoder.getTraitHeight());
    int value = content.getEssenceValue();
    traitEncoder.encodeDotsCenteredAndUngrouped(template.getTemplateGraphics(), new Position(0, DOT_PADDING), width, value, content.getEssenceMax());
    return template.getImageInstance();
  }

  private final void addEssenceHeader(PdfPTable table, Image firstCell, ExtendedEssenceContent content) {
    PdfPCell headerCell = new TableCell(firstCell);
    headerCell.setColspan(4);
    table.addCell(headerCell);
    table.addCell(spaceCell);
    table.addCell(createBigHeaderCell(content.getRegainHeaderLabel(), 3));

    table.addCell(spaceCell);
    table.addCell(createHeaderCell(content.getEssenceTotalHeaderLabel(), 1));
    table.addCell(createHeaderCell(content.getCommittedHeaderLabel(), 1));
    table.addCell(createHeaderCell(content.getAvailableHeaderLabel(), 1));
    table.addCell(spaceCell);
    table.addCell(spaceCell);
    table.addCell(createHeaderCell(content.getAtEaseHeaderLabel(), 1));
    table.addCell(createHeaderCell(content.getRelaxHeaderLabel(), 1));
  }

  private void addEssencePoolRows(PdfPTable table, ExtendedEssenceContent content) {
    List<PoolRow> poolRows = content.getPoolRows();
    List<RecoveryRow> recoveryRows = content.getRecoveryRows();
    for (int index = 0; index < content.getNumberOfContentLines(); index++) {
      addPoolCells(table, poolRows.get(index));
      addSeparator(table);
      addRecoveryCells(table, recoveryRows.get(index));
    }
  }

  private void addSeparator(PdfPTable table) {
    table.addCell(spaceCell);
  }

  private void addRecoveryCells(PdfPTable table, RecoveryRow recoveryRow) {
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

  private void addPoolCells(PdfPTable table, PoolRow poolRow) {
    Phrase labelPhrase = new Phrase(poolRow.getLabel(), font);
    table.addCell(new TableCell(labelPhrase, LABEL_BORDER, ALIGN_CENTER, ALIGN_MIDDLE));

    Phrase capacityPhrase = new Phrase(poolRow.getCapacity(), font);
    table.addCell(new TableCell(capacityPhrase, INTERNAL_BORDER, ALIGN_CENTER, ALIGN_MIDDLE));

    Phrase committedPhrase = new Phrase(poolRow.getCommitted(), font);
    PdfPCell committedCell = new TableCell(committedPhrase, INTERNAL_BORDER, ALIGN_CENTER, ALIGN_MIDDLE);
    if (!poolRow.isCommitmentEnabled()) {
      committedCell.setBackgroundColor(Color.LIGHT_GRAY);
    }
    table.addCell(committedCell);
    Phrase availablePhrase = new Phrase(poolRow.getAvailable(), font);
    table.addCell(new TableCell(availablePhrase, INTERNAL_BORDER, ALIGN_RIGHT, ALIGN_MIDDLE));
  }

  protected final PdfPCell createHeaderCell(String text, int columnSpan) {
    PdfPCell cell = new TableCell(new Phrase(text, font), HEADER_BORDER, ALIGN_CENTER, Element.ALIGN_BOTTOM);
    cell.setColspan(columnSpan);
    return cell;
  }

  protected final PdfPCell createBigHeaderCell(String text, int columnSpan) {
    PdfPCell cell = new TableCell(new Phrase(text, boldFont), HEADER_BORDER, ALIGN_CENTER, ALIGN_MIDDLE);
    cell.setColspan(columnSpan);
    return cell;
  }

  protected final Font getCommentFont() {
    return commentFont;
  }

  public boolean hasContent(ExtendedEssenceContent content) {
    return content.hasContent();
  }
}
