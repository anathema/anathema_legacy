package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionDbPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Simple1stEditionDbPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, essenceMax, registry.getBaseFont());
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new Db1stEditionGreatCurseEncoder(getResources());
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new DbAnimaEncoderFactory(getResources(), getBaseFont()).createAnimaEncoder();
  }
}
