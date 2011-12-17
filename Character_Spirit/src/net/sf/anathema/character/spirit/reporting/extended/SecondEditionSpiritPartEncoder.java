package net.sf.anathema.character.spirit.reporting.extended;

import net.sf.anathema.character.reporting.common.encoder.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.extended.page.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSpiritPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public SecondEditionSpiritPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  // TODO: This should be properly edited out, not just nulled out.
  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new NullPdfContentEncoder();
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new SpiritAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
}
