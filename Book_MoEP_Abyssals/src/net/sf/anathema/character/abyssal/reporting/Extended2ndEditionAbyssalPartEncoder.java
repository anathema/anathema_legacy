package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionAbyssalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndEditionAbyssalPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new Abyssal2ndResonanceEncoder(getBaseFont(), getResources());
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new Abyssal2ndAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
}
