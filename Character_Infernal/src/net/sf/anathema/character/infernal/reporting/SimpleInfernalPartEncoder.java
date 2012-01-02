package net.sf.anathema.character.infernal.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SimpleInfernalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public SimpleInfernalPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new InfernalUrgeEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new InfernalAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }
}
