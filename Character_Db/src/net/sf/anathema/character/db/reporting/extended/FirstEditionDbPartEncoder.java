package net.sf.anathema.character.db.reporting.extended;

import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.extended.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionDbPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public FirstEditionDbPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new FirstEditionDbGreatCurseEncoder(getBaseFont(), getResources());
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new DbAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
}
