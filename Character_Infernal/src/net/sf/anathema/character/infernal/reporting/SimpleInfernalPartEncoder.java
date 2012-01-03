package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SimpleInfernalPartEncoder extends AbstractSimplePartEncoder {

  public SimpleInfernalPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new InfernalUrgeEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new InfernalAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
