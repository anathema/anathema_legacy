package net.sf.anathema.character.solar.reporting.extended;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.common.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.common.TableEncodingUtilities;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.elements.Line;
import net.sf.anathema.character.reporting.common.encoder.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;

// TODO: Convert to a variable-height encoder
public class PdfSolarVirtueFlawEncoder implements IPdfContentBoxEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final Font nameFont;
  private final Font font;

  public PdfSolarVirtueFlawEncoder(BaseFont baseFont) {
    this.font = createFont(baseFont);
    this.nameFont = createNameFont(baseFont);
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "GreatCurse.Solar"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    float boxPadding = 1f;

    ISolarVirtueFlaw virtueFlaw = ((ISolarVirtueFlawModel) character.getAdditionalModel(SolarVirtueFlawTemplate.ID)).getVirtueFlaw();
    //Bounds textBounds = traitEncoder.encode(directContent, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    float boxHeight = traitEncoder.encodeHeight(directContent, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    float boxInterval = boxHeight + boxPadding;
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - boxInterval);

    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
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
  
  public boolean hasContent(IGenericCharacter character) {
	  return true;
  }
}
