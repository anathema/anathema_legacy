package net.sf.anathema.character.sidereal.reporting;

import java.awt.Color;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

public class ArcaneFateInfoEncoder implements IPdfContentBoxEncoder {

  private final BaseFont basefont;
  private final IResources resources;
  private final Chunk symbolChunk;
  private final IExaltedEdition edition;
  private final int fontSize;
  private float lineHeight;

  public ArcaneFateInfoEncoder(BaseFont baseFont, BaseFont symbolBaseFont, int fontSize, IResources resources,
		  IExaltedEdition edition) {
    this.fontSize = fontSize;
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
    this.resources = resources;
    this.basefont = baseFont;
    this.lineHeight = fontSize * 1.5f;
    this.edition = edition;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
	String rememberingResource = edition == ExaltedEdition.FirstEdition ?
			"Sheet.ArcaneFate.Remembering" : "Sheet.ArcaneFate.Remembering2nd";
    Phrase phrase = new Phrase("", new Font(basefont, fontSize, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Masquerade") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Disguise") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Destiny") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Relationship") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.ArcaneFate.Stealth") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString(rememberingResource) + "\n"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds, lineHeight);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Sidereal.ArcaneFate"; //$NON-NLS-1$
  }

}
