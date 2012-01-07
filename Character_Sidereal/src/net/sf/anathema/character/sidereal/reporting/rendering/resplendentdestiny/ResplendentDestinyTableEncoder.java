package net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny;

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

public class ResplendentDestinyTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;

  public ResplendentDestinyTableEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    PdfPTable table = new PdfPTable(new float[]{1f});
    Font commentFont = graphics.createCommentFont();
    Font font = graphics.createTableFont();

    Phrase destinyPhrase = new Phrase(resources.getString("Sheet.Header.Sidereal.ResplendentDestiny") + "\n\n", font);
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.ResplendentInfo1"), commentFont));
    destinyPhrase.add(new Chunk("\n\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.ResplendentInfo2"), commentFont));
    destinyPhrase.add(new Chunk("\n\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.ResplendentInfo3"), commentFont));
    destinyPhrase.add(new Chunk("\n\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.ResplendentInfo4"), commentFont));

    destinyPhrase.add(new Chunk("\n\n", commentFont));
    table.addCell(createContentCell(destinyPhrase));

    return table;
  }

  protected PdfPCell createContentCell(Phrase phrase) {
    return new TableCell(phrase, Rectangle.BOX);
  }
}
