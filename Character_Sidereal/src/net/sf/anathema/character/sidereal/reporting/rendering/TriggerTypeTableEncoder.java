package net.sf.anathema.character.sidereal.reporting.rendering;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.lib.resources.IResources;

public class TriggerTypeTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;

  public TriggerTypeTableEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    Font font = graphics.createTableFont();
    Font commentFont = graphics.createCommentFont();
    Font boldCommentFont = graphics.createCommentFont();
    boldCommentFont.setStyle(Font.BOLD);
    PdfPTable table = new PdfPTable(new float[]{1f});

    Phrase triggerPhrase = new Phrase(resources.getString("Sheet.Astrology.TriggerTypes") + "\n\n", font);
    triggerPhrase.add(new Chunk(resources.getString("Sheet.Astrology.Simple") + ": ", boldCommentFont));
    triggerPhrase.add(new Chunk(resources.getString("Sheet.Astrology.SimpleEffect") + "\n", commentFont));
    triggerPhrase.add(new Chunk(resources.getString("Sheet.Astrology.Intelligent") + ": ", boldCommentFont));
    triggerPhrase.add(new Chunk(resources.getString("Sheet.Astrology.IntelligentEffect") + "\n", commentFont));
    triggerPhrase.add(new Chunk("\n", commentFont));

    table.addCell(createContentCell(triggerPhrase));

    return table;
  }

  protected PdfPCell createContentCell(Phrase phrase) {
    return new TableCell(phrase, Rectangle.BOX);
  }
}
