package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionAbyssalPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionAbyssalPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new AbyssalResonanceEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new AbyssalAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
