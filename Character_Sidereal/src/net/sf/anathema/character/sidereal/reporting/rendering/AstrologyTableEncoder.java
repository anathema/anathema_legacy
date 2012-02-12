package net.sf.anathema.character.sidereal.reporting.rendering;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.CellPadding;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableList;
import net.sf.anathema.lib.resources.IResources;

public class AstrologyTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;

  public AstrologyTableEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    TableList list = new TableList(graphics.createTableFont(), new CellPadding(2, 0, 1, 1));
    TableCell spaceCell = new TableCell(new Phrase(" ", graphics.createCommentFont()), Rectangle.NO_BORDER); //$NON-NLS-1$
    spaceCell.setPadding(0);

    list.addItem(resources.getString("Sheet.Astrology.PlanningPhase")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.PlanDestinies")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.ComputeHoroscopes")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.RitualBehaviour")); //$NON-NLS-1$
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.PrayerPhase")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.CreatePetition")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.Cosignatures")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.Countersignatures")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.LengthyPrayer")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.PrayerRoll")); //$NON-NLS-1$
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.EffectPhase")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.MultipleParticipants")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.EffectRoll")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.ChooseEffect")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.ParadoxRoll")); //$NON-NLS-1$
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // list.addCell(spaceCell);
    // TableCell rulesCommentCell = new TableCell(
    // new Phrase(resources.getString("Sheet.Astrology.Rules"), commentFont), Rectangle.NO_BORDER); //$NON-NLS-1$
    // rulesCommentCell.setPadding(0);
    // list.addCell(rulesCommentCell);

    return list.getTable();
  }
}
