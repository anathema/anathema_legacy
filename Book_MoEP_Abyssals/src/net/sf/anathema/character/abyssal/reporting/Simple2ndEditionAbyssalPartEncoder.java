package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionAbyssalPartEncoder extends AbstractSimplePartEncoder {

  public Simple2ndEditionAbyssalPartEncoder(IResources resources, int essenceMax) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new Abyssal2ndResonanceEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new Abyssal2ndAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
