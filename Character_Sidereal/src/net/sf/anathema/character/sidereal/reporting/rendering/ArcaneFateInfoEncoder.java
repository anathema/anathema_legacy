package net.sf.anathema.character.sidereal.reporting.rendering;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class ArcaneFateInfoEncoder implements ContentEncoder {

  private final IResources resources;
  private final IExaltedEdition edition;
  private final int fontSize;
  private float lineHeight;

  public ArcaneFateInfoEncoder(int fontSize, IResources resources, IExaltedEdition edition) {
    this.fontSize = fontSize;
    this.resources = resources;
    this.lineHeight = fontSize * 1.5f;
    this.edition = edition;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    String rememberingResource = edition == ExaltedEdition.FirstEdition ? "Sheet.ArcaneFate.Remembering" : "Sheet.ArcaneFate.Remembering2nd";
    Chunk symbolChunk = graphics.createSymbolChunk();
    Phrase phrase = new Phrase("", graphics.createFont(fontSize)); //$NON-NLS-1$
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
    graphics.createSimpleColumn(bounds).withLeading(lineHeight).andTextPart(phrase).encode();
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Sidereal.ArcaneFate");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
