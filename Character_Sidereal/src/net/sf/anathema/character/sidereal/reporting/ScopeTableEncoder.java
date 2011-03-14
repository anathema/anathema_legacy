package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.elements.CellPadding;
import net.sf.anathema.character.reporting.sheet.elements.TableList;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableCell;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
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

public class ScopeTableEncoder extends AbstractTableEncoder {

  private final IResources resources;
  private final Font font;
  private final Font commentFont;
  private final Font boldCommentFont;

  public ScopeTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.boldCommentFont = TableEncodingUtilities.createCommentFont(baseFont);
    boldCommentFont.setStyle(Font.BOLD);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
	   TableList list = new TableList(commentFont, new CellPadding(2, 0, 1, 1));
	   TableCell spaceCell = new TableCell(new Phrase(" ", commentFont), Rectangle.NO_BORDER); //$NON-NLS-1$
	    spaceCell.setPadding(0);
	    
	    list.setIndex(0);

	    list.addHeader(new Chunk(resources.getString("Sheet.Astrology.Scope"), font), true); //$NON-NLS-1$
	    list.addCell(spaceCell);
	    list.addCell(spaceCell);
	    list.addItem(resources.getString("Sheet.Astrology.Scope.Individual")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.IndividualAlone")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.SmallGroup")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.ExtendedFamily")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.Clan")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.Town")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.City")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.Principality")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.Kingdom")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.LocalRegion")); //$NON-NLS-1$
	    list.addItem(resources.getString("Sheet.Astrology.Scope.AstrologicalDirection")); //$NON-NLS-1$
	    list.addCell(spaceCell);
	    list.addCell(spaceCell);	    

	    PdfPTable table = new PdfPTable(new float[] { 1f });
	    table.addCell(new TableCell(list.getTable(), Rectangle.BOX));
	    return table;
  }
  
  protected PdfPCell createContentCell(Phrase phrase) {
	    return new TableCell(phrase, Rectangle.BOX);
	  }
}