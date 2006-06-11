package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEssenceEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionSolarPartEncoder implements IPdfPartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final int essenceMax;
  private final BaseFont symbolBaseFont;

  public SecondEditionSolarPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    this.resources = resources;
    this.baseFont = registry.getBaseFont();
    this.symbolBaseFont = registry.getSymbolBaseFont();
    this.essenceMax = essenceMax;
  }

  public IPdfContentEncoder getAnimaEncoder() {
    return new PdfAnimaEncoder(resources, baseFont, symbolBaseFont);
  }

  public IPdfContentEncoder getEssenceEncoder() {
    return new PdfEssenceEncoder(baseFont, resources, essenceMax);
  }

  public boolean hasSecondPage() {
    return true;
  }

  public IPdfContentEncoder getGreatCurseEncoder() {
    return new PdfSolarVirtueFlawEncoder(baseFont);
  }
}