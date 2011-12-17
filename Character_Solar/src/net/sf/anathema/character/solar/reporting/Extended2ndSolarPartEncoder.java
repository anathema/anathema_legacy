package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndSolarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Extended2ndSolarPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new PdfSolarVirtueFlawEncoder(getBaseFont());
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new SolarAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
}
