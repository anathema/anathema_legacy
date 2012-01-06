package net.sf.anathema.character.solar.reporting.rendering;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.solar.reporting.content.VirtueFlawContent;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class VirtueFlawEncoder extends AbstractBoxContentEncoder<VirtueFlawContent> {

  public VirtueFlawEncoder() {
    super(VirtueFlawContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    VirtueFlawContent content = createContent(reportContent);
    VirtueFlawBoxEncoder traitEncoder = new VirtueFlawBoxEncoder();
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

  private void encodeNameAndConditionDefined(SheetGraphics graphics, VirtueFlawContent content, Bounds textBounds) throws DocumentException {
    Phrase phrase = new Phrase();
    phrase.add(new Chunk(content.getVirtueFlawName() + ": ", createNameFont(graphics)));
    phrase.add(new Chunk(content.getLimitBreakCondition(), createConditionFont(graphics)));
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  private void encodeOnlyNameDefined(SheetGraphics graphics, Bounds bounds, VirtueFlawContent content, Bounds textBounds) throws DocumentException {
    Phrase phrase = new Phrase();
    phrase.add(new Chunk(content.getVirtueFlawName(), createNameFont(graphics)));
    float baseLine = graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode().getYLine();
    encodeLines(graphics, bounds, REDUCED_LINE_HEIGHT, baseLine);
  }

  private void encodeOnlyConditionDefined(SheetGraphics graphics, VirtueFlawContent content, Bounds textBounds) throws DocumentException {
    Phrase phrase = new Phrase();
    Font undefinedFont = new Font(createNameFont(graphics));
    undefinedFont.setStyle(Font.UNDERLINE);
    phrase.add(new Chunk("                                          : ", undefinedFont)); //$NON-NLS-1$
    phrase.add(new Chunk(": ", createNameFont(graphics))); //$NON-NLS-1$
    phrase.add(new Chunk(content.getLimitBreakCondition(), createConditionFont(graphics)));
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  private void encodeLines(SheetGraphics graphics, Bounds bounds, float leading, float yPosition) {
    yPosition -= leading;
    while (yPosition > bounds.getMinY()) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode();
      yPosition -= leading;
    }
  }

  private Font createNameFont(SheetGraphics graphics) {
    Font newFont = graphics.createTableFont();
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createConditionFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }
}
