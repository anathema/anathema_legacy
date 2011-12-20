package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.general.QualifiedText;
import net.sf.anathema.character.reporting.pdf.content.general.TextType;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;

public abstract class AbstractCombatRulesTableEncoder extends AbstractTableEncoder<ReportContent> {

  @Override
  protected final PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) {
    float cellPadding = 0.05f;
    PdfPTable table = new PdfPTable(new float[]{1f, cellPadding, 1.1f, cellPadding, 1f});
    addFirstCell(graphics, content, table);
    table.addCell(createSpaceCell(graphics));
    addSecondCell(graphics, content, table);
    table.addCell(createSpaceCell(graphics));
    addThirdCell(graphics, content, table);
    return table;
  }

  protected abstract void addFirstCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table);

  protected abstract void addSecondCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table);

  protected abstract void addThirdCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table);

  private PdfPCell createSpaceCell(SheetGraphics graphics) {
    return new TableCell(new Phrase(" ", graphics.createTextFont()), Rectangle.NO_BORDER); //$NON-NLS-1$
  }

  protected PdfPCell createContentCell(Phrase phrase) {
    return new TableCell(phrase, Rectangle.BOX);
  }

  protected void addAsCell(SheetGraphics graphics, PdfPTable table, QualifiedText[] textChunks) {
    Phrase knockdownAndStunningPhrase = new Phrase("");
    for (QualifiedText text : textChunks) {
      knockdownAndStunningPhrase.add(createChunk(graphics, text));
    }
    table.addCell(createContentCell(knockdownAndStunningPhrase));
  }

  private final Chunk createChunk(SheetGraphics graphics, QualifiedText text) {
    Font font = text.type == TextType.Comment ? graphics.createCommentFont() : graphics.createTextFont();
    return new Chunk(text.text, font);
  }
}
