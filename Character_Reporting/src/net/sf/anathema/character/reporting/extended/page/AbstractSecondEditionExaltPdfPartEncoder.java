package net.sf.anathema.character.reporting.extended.page;

import net.sf.anathema.character.reporting.extended.PdfEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSecondEditionExaltPdfPartEncoder extends AbstractSecondEditionPartEncoder {

  public AbstractSecondEditionExaltPdfPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), registry.getSymbolBaseFont(), essenceMax);
  }
  
  public boolean hasMagicPage() {
	return true;
  }
}