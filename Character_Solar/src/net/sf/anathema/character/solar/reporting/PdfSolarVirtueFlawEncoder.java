package net.sf.anathema.character.solar.reporting;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.elements.Line;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSolarVirtueFlawEncoder implements IPdfContentBoxEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final Font nameFont;
  private final Font font;

  public PdfSolarVirtueFlawEncoder(BaseFont baseFont) {
    this.font = createFont(baseFont);
    this.nameFont = createNameFont(baseFont);
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
  }

  public String getHeaderKey() {
    return "GreatCurse.Solar"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Bounds textBounds = traitEncoder.encode(directContent, bounds);
    int leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    ISolarVirtueFlaw virtueFlaw = ((ISolarVirtueFlawModel) character.getAdditionalModel(IAdditionalTemplate.SOLAR_VIRTUE_FLAW_ID)).getVirtueFlaw();
    String name = virtueFlaw.getName().getText();
    String condition = virtueFlaw.getLimitBreak().getText();
    boolean nameDefined = !StringUtilities.isNullOrTrimEmpty(name);
    boolean conditionDefined = !StringUtilities.isNullOrEmpty(condition);
    if (!nameDefined && !conditionDefined) {
      encodeLines(directContent, bounds, leading, textBounds.getMaxY());
    }
    if (nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
    }
    if (nameDefined && !conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      ColumnText columnText = PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
      float baseLine = columnText.getYLine();
      encodeLines(directContent, bounds, leading, baseLine);
    }
    if (!nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      Font undefinedFont = new Font(nameFont);
      undefinedFont.setStyle(Font.UNDERLINE);
      phrase.add(new Chunk("                                          ", undefinedFont)); //$NON-NLS-1$
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
    }
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, int leading, float yPosition) {
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
}