package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.essence.SimpleEssenceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SMALLER_FONT_SIZE;

public abstract class AbstractFirstEditionExaltPdfPartEncoder extends AbstractFirstEditionPartEncoder {

  public AbstractFirstEditionExaltPdfPartEncoder(IResources resources) {
    super(resources);
  }

  public IBoxContentEncoder getEssenceEncoder() {
    return new SimpleEssenceBoxContentEncoder();
  }

  protected final int getFontSize() {
    return SMALLER_FONT_SIZE;
  }
}
