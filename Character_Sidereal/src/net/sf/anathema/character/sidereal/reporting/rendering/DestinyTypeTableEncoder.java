package net.sf.anathema.character.sidereal.reporting.rendering;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.lib.resources.IResources;

public class DestinyTypeTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;

  public DestinyTypeTableEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    Font font = graphics.createTableFont();
    Font commentFont = graphics.createCommentFont();
    Font boldCommentFont = graphics.createCommentFont();
    boldCommentFont.setStyle(Font.BOLD);
    PdfPTable table = new PdfPTable(new float[]{1f});

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
