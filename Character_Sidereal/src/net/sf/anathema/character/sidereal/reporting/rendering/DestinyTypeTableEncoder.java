package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
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

public class DestinyTypeTableEncoder extends AbstractTableEncoder {

  private final IResources resources;
  private final Font font;
  private final Font commentFont;
  private final Font boldCommentFont;

  public DestinyTypeTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.boldCommentFont = TableEncodingUtilities.createCommentFont(baseFont);
    boldCommentFont.setStyle(Font.BOLD);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, ReportContent content, Bounds bounds)
      throws DocumentException {
	PdfPTable table = new PdfPTable(new float[] { 1f });
    
    Phrase destinyPhrase = new Phrase(resources.getString("Sheet.Astrology.DestinyTypes") + "\n\n", font);
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.ArtlessProdigy") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.ArtlessProdigyEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.BlissfulIdiot") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.BlissfulIdiotEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.HoundChasesRabbit") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.HoundChasesRabbitEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.FortifiedSpirit") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.FortifiedSpiritEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.SlopedFloor") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.SlopedFloorEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.RuinWithoutFailure") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.RuinWithoutFailureEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.HeartPiercingCurse") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.HeartPiercingCurseEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.NameDestroyingCurse") + ": ", boldCommentFont));
    destinyPhrase.add(new Chunk(resources.getString("Sheet.Astrology.NameDestroyingCurseEffect") + "\n", commentFont));
    destinyPhrase.add(new Chunk("\n", commentFont));
    table.addCell(createContentCell(destinyPhrase));
    
    return table;
  }
  
  protected PdfPCell createContentCell(Phrase phrase) {
	    return new TableCell(phrase, Rectangle.BOX);
	  }
}
