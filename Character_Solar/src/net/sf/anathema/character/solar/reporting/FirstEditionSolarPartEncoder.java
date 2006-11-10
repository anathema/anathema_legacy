package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionSolarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public FirstEditionSolarPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new PdfSolarVirtueFlawEncoder(getBaseFont());
  }

  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new PdfAnimaEncoder(
        getResources(),
        getBaseFont(),
        getSymbolBaseFont(),
        getFontSize(),
        getAnimaTableEncoder());
  }

  private IPdfTableEncoder getAnimaTableEncoder() {
    return new SolarAnimaTableEncoder(getResources(), getBaseFont(), getFontSize());
  }
}