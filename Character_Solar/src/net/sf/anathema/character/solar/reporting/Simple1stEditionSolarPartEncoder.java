package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionSolarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Simple1stEditionSolarPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, essenceMax, registry.getBaseFont());
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SolarVirtueFlawContentBoxEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SolarAnimaEncoderFactory(getResources(), getBaseFont()).createAnimaEncoder();
  }
}
