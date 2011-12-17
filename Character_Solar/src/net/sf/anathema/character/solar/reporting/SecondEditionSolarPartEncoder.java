package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSolarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public SecondEditionSolarPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new PdfSolarVirtueFlawEncoder(getBaseFont());
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new SolarAnimaEncoderFactory(getResources(), getBaseFont(), getSymbolBaseFont()).createAnimaEncoder();
  }
}
