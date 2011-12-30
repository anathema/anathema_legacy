package net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class ResplendentDestinyTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;
  private final Font font;
  private final Font commentFont;

  public ResplendentDestinyTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createTableFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds)
      throws DocumentException {
	PdfPTable table = new PdfPTable(new float[] { 1f });
    
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
