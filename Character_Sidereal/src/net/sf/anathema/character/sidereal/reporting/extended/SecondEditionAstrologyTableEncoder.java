package net.sf.anathema.character.sidereal.reporting.extended;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.extended.elements.CellPadding;
import net.sf.anathema.character.reporting.extended.elements.TableList;
import net.sf.anathema.character.reporting.extended.util.AbstractTableEncoder;
import net.sf.anathema.character.reporting.extended.util.TableCell;
import net.sf.anathema.character.reporting.extended.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionAstrologyTableEncoder extends AbstractTableEncoder {

  private final IResources resources;
  private final Font font;
  private final Font commentFont;

  public SecondEditionAstrologyTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
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