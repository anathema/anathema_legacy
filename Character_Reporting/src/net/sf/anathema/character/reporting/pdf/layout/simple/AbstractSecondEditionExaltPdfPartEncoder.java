package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSecondEditionExaltPdfPartEncoder extends AbstractSecondEditionPartEncoder {

  public AbstractSecondEditionExaltPdfPartEncoder(IResources resources, BaseFont baseFont, int essenceMax) {
    super(resources, baseFont, essenceMax);
  }

  public boolean hasMagicPage() {
    return true;
  }
}
