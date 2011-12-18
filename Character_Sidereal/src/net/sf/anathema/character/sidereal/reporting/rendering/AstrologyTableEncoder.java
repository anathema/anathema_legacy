package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.elements.CellPadding;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableList;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableCell;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;

public class AstrologyTableEncoder extends AbstractTableEncoder<ReportContent> {

  private final IResources resources;
  private final Font font;
  private final Font commentFont;

  public AstrologyTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, ReportContent content, Bounds bounds)
      throws DocumentException {
    TableList list = new TableList(font, new CellPadding(2, 0, 1, 1));
    TableCell spaceCell = new TableCell(new Phrase(" ", commentFont), Rectangle.NO_BORDER); //$NON-NLS-1$
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
