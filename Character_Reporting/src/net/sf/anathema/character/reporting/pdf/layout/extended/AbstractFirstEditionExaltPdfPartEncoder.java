package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.essence.SimpleEssenceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.DotBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractFirstEditionExaltPdfPartEncoder extends AbstractFirstEditionPartEncoder {

  private final int essenceMax;

  public AbstractFirstEditionExaltPdfPartEncoder(IResources resources, BaseFont baseFont, int essenceMax) {
    super(resources, baseFont);
    this.essenceMax = essenceMax;
  }

  public ContentEncoder getEssenceEncoder() {
    return new SimpleEssenceBoxContentEncoder();
  }

  public ContentEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey) {
    return new DotBoxContentEncoder(trait, traitMax, traitHeaderKey);
  }

  public ContentEncoder getOverdriveEncoder() {
    return null;
  }

  protected int getEssenceMax() {
    return essenceMax;
  }

  public boolean hasMagicPage() {
    return true;
  }

  protected final int getFontSize() {
    return IVoidStateFormatConstants.SMALLER_FONT_SIZE;
  }
}
