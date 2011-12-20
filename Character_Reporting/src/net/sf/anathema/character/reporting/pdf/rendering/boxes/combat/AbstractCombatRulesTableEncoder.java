package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCombatRulesTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;
  private final Font commentFont;
  private final Font font;

  public AbstractCombatRulesTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.font = TableEncodingUtilities.createFont(baseFont);
  }

  protected final Font getCommentFont() {
    return commentFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected final Font getFont() {
    return font;
  }

  @Override
  protected final PdfPTable createTable(PdfContentByte directContent, ReportContent content, Bounds bounds) {
    float cellPadding = 0.05f;
    PdfPTable table = new PdfPTable(new float[]{1f, cellPadding, 1.1f, cellPadding, 1f});
    addFirstCell(content, table);
    table.addCell(createSpaceCell());
    addSecondCell(content, table);
    table.addCell(createSpaceCell());
    addThirdCell(content, table);
    return table;
  }

  protected abstract void addFirstCell(ReportContent reportContent, PdfPTable table);

  protected abstract void addSecondCell(ReportContent reportContent, PdfPTable table);

  protected abstract void addThirdCell(ReportContent reportContent, PdfPTable table);

  private PdfPCell createSpaceCell() {
    return new TableCell(new Phrase(" ", font), Rectangle.NO_BORDER); //$NON-NLS-1$
  }

  protected PdfPCell createContentCell(Phrase phrase) {
    return new TableCell(phrase, Rectangle.BOX);
  }
}
