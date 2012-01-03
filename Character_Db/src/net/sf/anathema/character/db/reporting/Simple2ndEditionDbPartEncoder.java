package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionDbPartEncoder extends AbstractSimplePartEncoder {

  public Simple2ndEditionDbPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder() {
    return new Db2ndEditionGreatCurseEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new DbAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
