package net.sf.anathema.character.abyssal.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionAbyssalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Extended2ndEditionAbyssalPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new Abyssal2ndResonanceEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new Abyssal2ndAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }
}
