package net.sf.anathema.character.spirit.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSpiritPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public SecondEditionSpiritPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  // TODO: This should be properly edited out, not just nulled out.
  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new NullPdfContentEncoder();
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new SpiritAnimaEncoderFactory(getResources(), getBaseFont(), getSymbolBaseFont()).createAnimaEncoder();
  }
}
