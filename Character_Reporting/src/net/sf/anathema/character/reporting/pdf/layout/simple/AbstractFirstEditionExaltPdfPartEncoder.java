package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.essence.SimpleEssenceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractFirstEditionExaltPdfPartEncoder extends AbstractFirstEditionPartEncoder {

  private final int essenceMax;

  public AbstractFirstEditionExaltPdfPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), registry.getSymbolBaseFont());
    this.essenceMax = essenceMax;
  }

  public IBoxContentEncoder getEssenceEncoder() {
    return new SimpleEssenceBoxContentEncoder();
  }

  protected int getEssenceMax() {
    return essenceMax;
  }

  public boolean hasSecondPage() {
    return true;
  }

  protected final int getFontSize() {
    return IVoidStateFormatConstants.SMALLER_FONT_SIZE;
  }
}
