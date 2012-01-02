package net.sf.anathema.character.sidereal.reporting.rendering.astrology;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.CellPadding;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableList;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionAstrologyTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;

  public SecondEditionAstrologyTableEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    Font commentFont = graphics.createCommentFont();
    Font font = graphics.createTableFont();
    TableList list = new TableList(commentFont, new CellPadding(2, 0, 1, 1));
    TableCell spaceCell = new TableCell(new Phrase(" ", commentFont), Rectangle.NO_BORDER); //$NON-NLS-1$
    spaceCell.setPadding(0);

    list.addHeader(new Chunk(resources.getString("Sheet.Header.Sidereal.Astrology"), font), true); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Astrology.PrayerPhase")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.Charms")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.LengthyPrayer2nd")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.CreatePetition2nd")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.Cosignatures2nd")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.Countersignatures2nd")); //$NON-NLS-1$
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.EffectRoll")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.ComposePlans")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.ComputeHoroscopes2nd")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.RitualBehaviour2nd")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.MultipleParticipants2nd")); //$NON-NLS-1$
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.EffectPhase")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.ChooseEffect")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.ParadoxRoll")); //$NON-NLS-1$
    list.addCell(spaceCell);
    list.addCell(spaceCell);

    PdfPTable table = new PdfPTable(new float[] { 1f });
    table.addCell(new TableCell(list.getTable(), Rectangle.BOX));
    return table;
  }
}
