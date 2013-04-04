package net.sf.anathema.character.sidereal.reporting.rendering;

import com.itextpdf.text.DocumentException;
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

public class AstrologyTableEncoder extends AbstractTableEncoder<ReportSession> {

  private final Resources resources;

  public AstrologyTableEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    TableList list = new TableList(graphics.createTableFont(), new CellPadding(2, 0, 1, 1));
    TableCell spaceCell = new TableCell(new Phrase(" ", graphics.createCommentFont()), Rectangle.NO_BORDER);
    spaceCell.setPadding(0);

    list.addItem(resources.getString("Sheet.Astrology.PlanningPhase"));
    list.addSubItem(resources.getString("Sheet.Astrology.PlanDestinies"));
    list.addSubItem(resources.getString("Sheet.Astrology.ComputeHoroscopes"));
    list.addSubItem(resources.getString("Sheet.Astrology.RitualBehaviour"));
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.PrayerPhase"));
    list.addSubItem(resources.getString("Sheet.Astrology.CreatePetition"));
    list.addSubItem(resources.getString("Sheet.Astrology.Cosignatures"));
    list.addSubItem(resources.getString("Sheet.Astrology.Countersignatures"));
    list.addSubItem(resources.getString("Sheet.Astrology.LengthyPrayer"));
    list.addSubItem(resources.getString("Sheet.Astrology.PrayerRoll"));
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.EffectPhase"));
    list.addSubItem(resources.getString("Sheet.Astrology.MultipleParticipants"));
    list.addSubItem(resources.getString("Sheet.Astrology.EffectRoll"));
    list.addSubItem(resources.getString("Sheet.Astrology.ChooseEffect"));
    list.addSubItem(resources.getString("Sheet.Astrology.ParadoxRoll"));
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // TableCell rulesCommentCell = new TableCell(
    // new Phrase(resources.getString("Sheet.Astrology.Rules"), commentFont), Rectangle.NO_BORDER);
    // rulesCommentCell.setPadding(0);
    // list.addCell(rulesCommentCell);

    return list.getTable();
  }
}
