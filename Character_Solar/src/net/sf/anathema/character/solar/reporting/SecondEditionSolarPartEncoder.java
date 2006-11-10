package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionSolarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private final BaseFont symbolBaseFont;

  public SecondEditionSolarPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.symbolBaseFont = registry.getSymbolBaseFont();
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new PdfSolarVirtueFlawEncoder(getBaseFont());
  }

  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new PdfAnimaEncoder(getResources(), getBaseFont(), symbolBaseFont, getFontSize(), getAnimaTableEncoder());
  }

  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new SolarAnimaTableEncoder(getResources(), getBaseFont(), getFontSize());
  }
}