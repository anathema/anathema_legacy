package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionDbPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Simple1stEditionDbPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new Db1stEditionGreatCurseEncoder(getResources());
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new DbAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
