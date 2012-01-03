package net.sf.anathema.character.solar.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndSolarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {
  private BaseFont baseFont;

  public Extended2ndSolarPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new SolarVirtueFlawContentBoxEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new SolarAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
