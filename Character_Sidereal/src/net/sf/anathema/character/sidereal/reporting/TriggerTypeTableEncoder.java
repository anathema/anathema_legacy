package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.elements.CellPadding;
import net.sf.anathema.character.reporting.sheet.elements.TableList;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableCell;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class TriggerTypeTableEncoder extends AbstractTableEncoder {

  private final IResources resources;
  private final Font font;
  private final Font commentFont;

  public TriggerTypeTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
    TableList list = new TableList(font, new CellPadding(2, 0, 1, 1));
    TableCell spaceCell = new TableCell(new Phrase(" ", commentFont), Rectangle.NO_BORDER); //$NON-NLS-1$
    spaceCell.setPadding(0);

    list.addItem(resources.getString("Sheet.Astrology.TriggerTypes")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.Simple")); //$NON-NLS-1$
    list.addSubItem(resources.getString("Sheet.Astrology.Intelligent")); //$NON-NLS-1$
    
    return list.getTable();
  }
  
  protected PdfPCell createContentCell(Phrase phrase) {
	    return new TableCell(phrase, Rectangle.BOX);
	  }
}