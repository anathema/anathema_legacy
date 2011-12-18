package net.sf.anathema.character.lunar.reporting.rendering.greatcurse;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.lunar.virtueflaw.model.ILunarVirtueFlaw;
import net.sf.anathema.character.lunar.virtueflaw.presenter.ILunarVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Line;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionLunarGreatCurseEncoder implements IBoxContentEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final Font nameFont;
  private final Font font;

  public SecondEditionLunarGreatCurseEncoder(BaseFont baseFont) {
    this.font = createFont(baseFont);
    this.nameFont = createNameFont(baseFont);
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "GreatCurse.Lunar"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    ILunarVirtueFlaw virtueFlaw = ((ILunarVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(LunarVirtueFlawTemplate.TEMPLATE_ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(graphics.getDirectContent(), graphics.getBounds(), virtueFlaw.getLimitTrait().getCurrentValue());
    String name = virtueFlaw.getName().getText();
    String condition = virtueFlaw.getLimitBreak().getText();
    boolean nameDefined = !StringUtilities.isNullOrTrimEmpty(name);
    boolean conditionDefined = !StringUtilities.isNullOrEmpty(condition);
    if (!nameDefined && !conditionDefined) {
      encodeLines(graphics.getDirectContent(), graphics.getBounds(), leading, textBounds.getMaxY());
    }
    if (nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), phrase, textBounds, leading);
    }
    if (nameDefined && !conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      ColumnText columnText = PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), phrase, textBounds, leading);
      float baseLine = columnText.getYLine();
      encodeLines(graphics.getDirectContent(), graphics.getBounds(), leading, baseLine);
    }
    if (!nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      Font undefinedFont = new Font(nameFont);
      undefinedFont.setStyle(Font.UNDERLINE);
      phrase.add(new Chunk("                                          ", undefinedFont)); //$NON-NLS-1$
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), phrase, textBounds, leading);
    }
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, float leading, float yPosition) {
    yPosition -= leading;
    while (yPosition > bounds.getMinY()) {
      Line.createHorizontalByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode(directContent);
      yPosition -= leading;
    }
  }

  private Font createNameFont(BaseFont baseFont) {
    Font newFont = createFont(baseFont);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createFont(baseFont);
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
