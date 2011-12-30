package net.sf.anathema.character.solar.reporting;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class SolarVirtueFlawContentBoxEncoder implements IBoxContentEncoder {

  private final Font nameFont;
  private final Font font;

  public SolarVirtueFlawContentBoxEncoder(BaseFont baseFont) {
    this.font = TableEncodingUtilities.createTableFont(baseFont);
    this.nameFont = createNameFont(baseFont);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    SolarVirtueFlawContent content = createContent(reportContent);
    VirtueFlawBoxEncoder traitEncoder   = new VirtueFlawBoxEncoder();
    float traitHeight = traitEncoder.encodeHeight(graphics, bounds, content.getLimitValue());
    float traitInterval = traitHeight + 1f;
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - traitInterval);
    if (!content.isNameDefined() && !content.isConditionDefined()) {
      encodeLines(graphics, bounds, REDUCED_LINE_HEIGHT, textBounds.getMaxY());
    }
    if (content.isNameDefined() && content.isConditionDefined()) {
      encodeNameAndConditionDefined(graphics, content, textBounds);
    }
    if (content.isNameDefined() && !content.isConditionDefined()) {
      encodeOnlyNameDefined(graphics, bounds, content, textBounds);
    }
    if (!content.isNameDefined() && content.isConditionDefined()) {
      encodeOnlyConditionDefined(graphics, content, textBounds);
    }
  }

  private void encodeNameAndConditionDefined(SheetGraphics graphics, SolarVirtueFlawContent content, Bounds textBounds) throws DocumentException {
    Phrase phrase = new Phrase();
    phrase.add(new Chunk(content.getVirtueFlawName(), nameFont));
    phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
    phrase.add(new Chunk(content.getLimitBreakCondition(), font));
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  private void encodeOnlyNameDefined(SheetGraphics graphics, Bounds bounds, SolarVirtueFlawContent content, Bounds textBounds)
    throws DocumentException {
    Phrase phrase = new Phrase();
    phrase.add(new Chunk(content.getVirtueFlawName(), nameFont));
    float baseLine = graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode().getYLine();
    encodeLines(graphics, bounds, REDUCED_LINE_HEIGHT, baseLine);
  }

  private void encodeOnlyConditionDefined(SheetGraphics graphics, SolarVirtueFlawContent content, Bounds textBounds) throws DocumentException {
    Phrase phrase = new Phrase();
    Font undefinedFont = new Font(nameFont);
    undefinedFont.setStyle(Font.UNDERLINE);
    phrase.add(new Chunk("                                          ", undefinedFont)); //$NON-NLS-1$
    phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
    phrase.add(new Chunk(content.getLimitBreakCondition(), font));
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  private void encodeLines(SheetGraphics graphics, Bounds bounds, float leading, float yPosition) {
    yPosition -= leading;
    while (yPosition > bounds.getMinY()) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode();
      yPosition -= leading;
    }
  }

  private Font createNameFont(BaseFont baseFont) {
    Font newFont = TableEncodingUtilities.createTableFont(baseFont);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private SolarVirtueFlawContent createContent(ReportContent content) {
    return content.createSubContent(SolarVirtueFlawContent.class);
  }

  public boolean hasContent(ReportContent content) {
    return createContent(content).hasContent();
  }

  public String getHeaderKey(ReportContent content) {
    return createContent(content).getHeaderKey();
  }
}
