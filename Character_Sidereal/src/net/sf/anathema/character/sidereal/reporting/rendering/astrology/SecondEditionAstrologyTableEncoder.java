package net.sf.anathema.character.sidereal.reporting.rendering.astrology;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.CellPadding;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableList;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionAstrologyTableEncoder extends AbstractTableEncoder<ReportSession> {

  private final Resources resources;

  public SecondEditionAstrologyTableEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    Font commentFont = graphics.createCommentFont();
    Font font = graphics.createTableFont();
    TableList list = new TableList(commentFont, new CellPadding(2, 0, 1, 1));
    TableCell spaceCell = new TableCell(new Phrase(" ", commentFont), Rectangle.NO_BORDER);
    spaceCell.setPadding(0);

    list.addHeader(new Chunk(resources.getString("Sheet.Header.Sidereal.Astrology"), font), true);
    list.addItem(resources.getString("Sheet.Astrology.PrayerPhase"));
    list.addSubItem(resources.getString("Sheet.Astrology.Charms"));
    list.addSubItem(resources.getString("Sheet.Astrology.LengthyPrayer2nd"));
    list.addSubItem(resources.getString("Sheet.Astrology.CreatePetition2nd"));
    list.addSubItem(resources.getString("Sheet.Astrology.Cosignatures2nd"));
    list.addSubItem(resources.getString("Sheet.Astrology.Countersignatures2nd"));
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.EffectRoll"));
    list.addSubItem(resources.getString("Sheet.Astrology.ComposePlans"));
    list.addSubItem(resources.getString("Sheet.Astrology.ComputeHoroscopes2nd"));
    list.addSubItem(resources.getString("Sheet.Astrology.RitualBehaviour2nd"));
    list.addSubItem(resources.getString("Sheet.Astrology.MultipleParticipants2nd"));
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.EffectPhase"));
    list.addSubItem(resources.getString("Sheet.Astrology.ChooseEffect"));
    list.addSubItem(resources.getString("Sheet.Astrology.ParadoxRoll"));
    list.addCell(spaceCell);
    list.addCell(spaceCell);

    PdfPTable table = new PdfPTable(new float[]{1f});
    table.addCell(new TableCell(list.getTable(), Rectangle.BOX));
    return table;
  }
}
