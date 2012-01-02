package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SMALLER_FONT_SIZE;

public abstract class AbstractFirstEditionExaltPdfPartEncoder extends AbstractFirstEditionPartEncoder {

  public AbstractFirstEditionExaltPdfPartEncoder(IResources resources) {
    super(resources);
  }

  protected final int getFontSize() {
    return SMALLER_FONT_SIZE;
  }
}
