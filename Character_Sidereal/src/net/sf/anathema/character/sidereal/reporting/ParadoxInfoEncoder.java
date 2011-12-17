package net.sf.anathema.character.sidereal.reporting;

import java.awt.Color;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class ParadoxInfoEncoder implements IPdfContentBoxEncoder {

  private final IExaltedEdition edition;
  private final BaseFont basefont;
  private final IResources resources;
  private final Chunk symbolChunk;
  private final int fontSize;
  private float lineHeight;

  public ParadoxInfoEncoder(BaseFont baseFont, BaseFont symbolBaseFont,
		  int fontSize, IResources resources, IExaltedEdition edition) {
    this.fontSize = fontSize;
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
    this.resources = resources;
    this.basefont = baseFont;
    this.lineHeight = fontSize * 1.5f;
    this.edition = edition;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
	  String animaResource = edition == ExaltedEdition.FirstEdition ?
			  "Sheet.Paradox.AnimaHigh" : "Sheet.Paradox.AnimaHigh2nd";
    Phrase phrase = new Phrase("", new Font(basefont, fontSize, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.OutOfCharacter") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.Resident") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.Anima") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString("Sheet.Paradox.AnimaLow") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString(animaResource) + "\n"); //$NON-NLS-1$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.ConfusionExalted") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendent") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendentImitation") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendentSupernatural") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds, lineHeight);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Sidereal.Paradox"; //$NON-NLS-1$
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
