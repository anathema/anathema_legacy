package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class SimpleInfernalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public SimpleInfernalPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new InfernalUrgeEncoder(getResources(), getBaseFont());
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new InfernalAnimaEncoderFactory(getResources(), getBaseFont(), getSymbolBaseFont()).createAnimaEncoder();
  }
}
