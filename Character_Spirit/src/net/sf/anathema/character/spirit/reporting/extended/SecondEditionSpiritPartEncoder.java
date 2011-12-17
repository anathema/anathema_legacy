package net.sf.anathema.character.spirit.reporting.extended;

import net.sf.anathema.character.reporting.extended.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.common.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.extended.page.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSpiritPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public SecondEditionSpiritPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
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