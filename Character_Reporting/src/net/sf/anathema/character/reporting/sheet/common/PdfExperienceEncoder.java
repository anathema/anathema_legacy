package net.sf.anathema.character.reporting.sheet.common;

import java.text.MessageFormat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfExperienceEncoder implements IPdfContentBoxEncoder {

  private static final int FONT_SIZE = IVoidStateFormatConstants.FONT_SIZE;
  private final Font font;
  private final IResources resources;

  public PdfExperienceEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.font = PdfTextEncodingUtilities.createFont(baseFont, FONT_SIZE);
  }

  public String getHeaderKey() {
    return "Experience"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    int totalPoints = character.getTotalExperiencePoints();
    int spentPoints = character.getSpentExperiencePoints();
    String experienceMessage = resources.getString("Sheet.Experience.MessageFormat"); //$NON-NLS-1$
    String experienceText = MessageFormat.format(experienceMessage, new Object[] {
        new Integer(totalPoints),
        new Integer(spentPoints),
        new Integer(totalPoints - spentPoints) });
    Phrase phrase = new Phrase(experienceText, font);
    PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds, FONT_SIZE + 4);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}