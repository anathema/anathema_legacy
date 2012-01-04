package net.sf.anathema.character.lunar.reporting.rendering;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.lunar.renown.RenownTemplate;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class LunarFaceEncoder extends AbstractTableEncoder<ReportContent> implements ContentEncoder {

  private final IResources resources;

  public LunarFaceEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    encodeTable(graphics, reportContent, bounds);
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    PdfPTable table = new PdfPTable(new float[]{6, 0.2f, 2.5f});
    table.setTotalWidth(bounds.width);
    float lineOffset = encodeContent(graphics, table, content);
    float delimitingLineYPosition = bounds.getMaxY() - lineOffset - 1;
    drawDelimiter(graphics.getDirectContent(), bounds, delimitingLineYPosition);
    return table;
  }

  protected float encodeContent(SheetGraphics graphics, PdfPTable table, ReportContent content) {
    IRenownModel model = (IRenownModel) content.getCharacter().getAdditionalModel(RenownTemplate.TEMPLATE_ID);
    ITrait[] traits = model.getAllTraits();
    for (ITrait trait : traits) {
      encodeRenownTraitLine(graphics, table, trait);
    }
    float height = table.getTotalHeight();
    int currentRank = model.getFace().getCurrentValue();
    String totalString = resources.getString("Lunar.Renown.FaceLabel") + " " //$NON-NLS-1$//$NON-NLS-2$
            + currentRank + ": " //$NON-NLS-1$
            + resources.getString("Lunar.Renown.Rank." + currentRank) //$NON-NLS-1$
            + " / " //$NON-NLS-1$
            + resources.getString("Lunar.Renown.RenownTotal"); //$NON-NLS-1$
    encodeRenownLine(graphics, table, totalString, model.calculateTotalRenown());
    return height;
  }

  private void drawDelimiter(PdfContentByte directContent, Bounds bounds, float delimitingLineYPosition) {
    directContent.moveTo(bounds.getMinX() + 3, delimitingLineYPosition);
    directContent.lineTo(bounds.getMaxX() - 3, delimitingLineYPosition);
    directContent.setColorStroke(Color.GRAY);
    directContent.setLineWidth(0.5f);
    directContent.stroke();
    directContent.setColorStroke(Color.BLACK);
  }

  private void encodeRenownTraitLine(SheetGraphics graphics, PdfPTable table, ITrait trait) {
    String text = resources.getString("Lunar.Renown." + trait.getType().getId()); //$NON-NLS-1$
    encodeRenownLine(graphics, table, text, trait.getCurrentValue());
  }

  private void encodeRenownLine(SheetGraphics graphics, PdfPTable table, String text, int value) {
    table.addCell(createTextCell(graphics, text, Element.ALIGN_LEFT));
    table.addCell(createSpaceCell(graphics));
    table.addCell(createTextCell(graphics, String.valueOf(value), Element.ALIGN_RIGHT));
  }

  private final PdfPCell createTextCell(SheetGraphics graphics, String text, int alignment) {
    PdfPCell cell = TableEncodingUtilities.createContentCellTable(Color.BLACK, text, createFont(graphics), 0.5f, Rectangle.NO_BORDER, alignment);
    cell.setPadding(0);
    return cell;
  }

  private PdfPCell createSpaceCell(SheetGraphics graphics) {
    PdfPCell cell = new PdfPCell(new Phrase("", createFont(graphics))); //$NON-NLS-1$
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private Font createFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Lunar.Face");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
