package net.sf.anathema.character.spirit.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractSecondEditionExaltPdfPartEncoder;
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