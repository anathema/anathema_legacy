package net.sf.anathema.character.sidereal.reporting.rendering;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class ArcaneFateInfoEncoder implements IBoxContentEncoder {

  private final BaseFont basefont;
  private final IResources resources;
  private final IExaltedEdition edition;
  private final int fontSize;
  private float lineHeight;

  public ArcaneFateInfoEncoder(BaseFont baseFont, int fontSize, IResources resources, IExaltedEdition edition) {
    this.fontSize = fontSize;
    this.resources = resources;
    this.basefont = baseFont;
    this.lineHeight = fontSize * 1.5f;
    this.edition = edition;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
	String rememberingResource = edition == ExaltedEdition.FirstEdition ?
			"Sheet.ArcaneFate.Remembering" : "Sheet.ArcaneFate.Remembering2nd";
    Chunk symbolChunk = graphics.createSymbolChunk();
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
    graphics.encodeText(phrase, bounds, lineHeight);
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }

  public String getHeaderKey(ReportContent content) {
    return "Sidereal.ArcaneFate"; //$NON-NLS-1$
  }

}
