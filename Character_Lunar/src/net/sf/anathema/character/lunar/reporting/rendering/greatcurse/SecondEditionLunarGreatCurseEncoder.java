package net.sf.anathema.character.lunar.reporting.rendering.greatcurse;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.lunar.virtueflaw.model.ILunarVirtueFlaw;
import net.sf.anathema.character.lunar.virtueflaw.presenter.ILunarVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

public class SecondEditionLunarGreatCurseEncoder implements IBoxContentEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final Font nameFont;
  private final Font font;

  public SecondEditionLunarGreatCurseEncoder(BaseFont baseFont) {
    this.font = createFont(baseFont);
    this.nameFont = createNameFont(baseFont);
    this.traitEncoder = new VirtueFlawBoxEncoder();
  }

  public String getHeaderKey(ReportContent content) {
    return "GreatCurse.Lunar"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    ILunarVirtueFlaw virtueFlaw =
      ((ILunarVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(LunarVirtueFlawTemplate.TEMPLATE_ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(graphics, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    String name = virtueFlaw.getName().getText();
    String condition = virtueFlaw.getLimitBreak().getText();
    boolean nameDefined = !StringUtilities.isNullOrTrimEmpty(name);
    boolean conditionDefined = !StringUtilities.isNullOrEmpty(condition);
    if (!nameDefined && !conditionDefined) {
      encodeLines(graphics, bounds, leading, textBounds.getMaxY());
    }
    if (nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      graphics.createSimpleColumn(textBounds).withLeading(leading).andTextPart(phrase).encode();
    }
    if (nameDefined && !conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      float baseLine = graphics.createSimpleColumn(textBounds).withLeading(leading).andTextPart(phrase).encode().getYLine();
      encodeLines(graphics, bounds, leading, baseLine);
    }
    if (!nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      Font undefinedFont = new Font(nameFont);
      undefinedFont.setStyle(Font.UNDERLINE);
      phrase.add(new Chunk("                                          ", undefinedFont)); //$NON-NLS-1$
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      graphics.createSimpleColumn(textBounds).withLeading(leading).andTextPart(phrase).encode();
    }
  }

  private void encodeLines(SheetGraphics graphics, Bounds bounds, float leading, float yPosition) {
    yPosition -= leading;
    while (yPosition > bounds.getMinY()) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode();
      yPosition -= leading;
    }
  }

  private Font createNameFont(BaseFont baseFont) {
    Font newFont = createFont(baseFont);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createTableFont(baseFont);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
