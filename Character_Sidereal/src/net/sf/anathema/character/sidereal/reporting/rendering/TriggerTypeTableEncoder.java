package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableCell;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class TriggerTypeTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;
  private final Font font;
  private final Font commentFont;
  private final Font boldCommentFont;

  public TriggerTypeTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.boldCommentFont = TableEncodingUtilities.createCommentFont(baseFont);
    boldCommentFont.setStyle(Font.BOLD);
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds)
      throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 1f });
	    
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
