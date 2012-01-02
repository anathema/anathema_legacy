package net.sf.anathema.character.abyssal.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionAbyssalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Simple2ndEditionAbyssalPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new Abyssal2ndResonanceEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new Abyssal2ndAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }
}
