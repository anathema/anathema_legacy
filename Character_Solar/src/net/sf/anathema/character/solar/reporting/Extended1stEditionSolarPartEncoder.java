package net.sf.anathema.character.solar.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionSolarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Extended1stEditionSolarPartEncoder(IResources resources, BaseFont baseFont, int essenceMax) {
    super(resources, baseFont, essenceMax);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SolarVirtueFlawContentBoxEncoder();
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SolarAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
