package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionSolarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionSolarPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SolarVirtueFlawContentBoxEncoder(getBaseFont());
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SolarAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
}
