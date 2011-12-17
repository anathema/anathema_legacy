package net.sf.anathema.character.reporting.sheet.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.elements.Line;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;

public abstract class AbstractLineTextEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {
  private final static float LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final BaseFont baseFont;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public AbstractLineTextEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    Font font = TableEncodingUtilities.createFont(baseFont);
    Phrase phrase = new Phrase();
    addToPhrase(character, font, phrase);
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - 2);
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, LINE_HEIGHT).getYLine();
    yPosition -= LINE_HEIGHT;
    while (yPosition > bounds.y) {
      Line.createHorizontalByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode(directContent);
      yPosition -= LINE_HEIGHT;
    }
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }

  protected abstract void addToPhrase(IGenericCharacter character, Font font, Phrase phrase);
}
