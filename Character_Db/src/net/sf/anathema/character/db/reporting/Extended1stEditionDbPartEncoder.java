package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionDbPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionDbPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new Db1stEditionGreatCurseEncoder(getResources());
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new DbAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
}
