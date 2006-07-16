package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableCell;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractCombatRulesTableEncoder extends AbstractTableEncoder {

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
  protected final PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    float cellPadding = 0.05f;
    PdfPTable table = new PdfPTable(new float[] { 1f, cellPadding, 1.1f, cellPadding, 1f });
    addFirstCell(table);
    table.addCell(createSpaceCell());
    addSecondCell(table);
    table.addCell(createSpaceCell());
    addThirdCell(table);
    return table;
  }

  protected abstract void addFirstCell(PdfPTable table);

  protected abstract void addSecondCell(PdfPTable table);

  protected abstract void addThirdCell(PdfPTable table);

  private PdfPCell createSpaceCell() {
    return new TableCell(new Phrase(" ", font), Rectangle.NO_BORDER); //$NON-NLS-1$
  }

  protected PdfPCell createContentCell(Phrase phrase) {
    return new TableCell(phrase, Rectangle.BOX);
  }
}