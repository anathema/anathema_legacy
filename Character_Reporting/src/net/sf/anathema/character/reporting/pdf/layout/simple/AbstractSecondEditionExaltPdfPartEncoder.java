package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractSecondEditionExaltPdfPartEncoder extends AbstractSecondEditionPartEncoder {

  public AbstractSecondEditionExaltPdfPartEncoder(IResources resources) {
    super(resources);
  }

  public boolean hasMagicPage() {
    return true;
  }
}
