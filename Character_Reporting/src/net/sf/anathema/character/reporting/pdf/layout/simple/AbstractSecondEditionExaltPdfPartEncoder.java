package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSecondEditionExaltPdfPartEncoder extends AbstractSecondEditionPartEncoder {

  public AbstractSecondEditionExaltPdfPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), registry.getSymbolBaseFont(), essenceMax);
  }

  public boolean hasMagicPage() {
    return true;
  }
}
