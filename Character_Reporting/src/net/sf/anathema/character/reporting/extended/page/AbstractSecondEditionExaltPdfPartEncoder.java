package net.sf.anathema.character.reporting.extended.page;

import net.sf.anathema.character.reporting.extended.ExtendedEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSecondEditionExaltPdfPartEncoder extends AbstractSecondEditionPartEncoder {

  public AbstractSecondEditionExaltPdfPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), registry.getSymbolBaseFont(), essenceMax);
  }

  public boolean hasMagicPage() {
    return true;
  }
}
