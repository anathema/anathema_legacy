package net.sf.anathema.character.sidereal.reporting.rendering;

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

public class ScopeTableEncoder extends AbstractTableEncoder<ReportSession> {

  private final Resources resources;

  public ScopeTableEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    Font font = graphics.createTableFont();
    Font commentFont = graphics.createCommentFont();
    Font boldCommentFont = graphics.createCommentFont();
    boldCommentFont.setStyle(Font.BOLD);
    TableList list = new TableList(commentFont, new CellPadding(2, 0, 1, 1));
    TableCell spaceCell = new TableCell(new Phrase(" ", commentFont), Rectangle.NO_BORDER);
    spaceCell.setPadding(0);

    list.setIndex(0);

    list.addHeader(new Chunk(resources.getString("Sheet.Astrology.Scope"), font), true);
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addItem(resources.getString("Sheet.Astrology.Scope.Individual"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.IndividualAlone"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.SmallGroup"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.ExtendedFamily"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.Clan"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.Town"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.City"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.Principality"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.Kingdom"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.LocalRegion"));
    list.addItem(resources.getString("Sheet.Astrology.Scope.AstrologicalDirection"));
    list.addCell(spaceCell);
    list.addCell(spaceCell);

    PdfPTable table = new PdfPTable(new float[]{1f});
    table.addCell(new TableCell(list.getTable(), Rectangle.BOX));
    return table;
  }
}