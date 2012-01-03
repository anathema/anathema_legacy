package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionAbyssalPartEncoder extends AbstractSimplePartEncoder {

  public Simple1stEditionAbyssalPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new AbyssalResonanceEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new AbyssalAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
