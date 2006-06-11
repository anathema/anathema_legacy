package net.sf.anathema.character.reporting.sheet.second.part;

import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEssenceEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionSolarPartEncoder implements IPdfPartEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final int essenceMax;

  public SecondEditionSolarPartEncoder(IResources resources, BaseFont baseFont, int essenceMax) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.essenceMax = essenceMax;
  }

  public IPdfContentEncoder getEssenceEncoder() {
    return new PdfEssenceEncoder(baseFont, resources, essenceMax);
  }

  public boolean hasSecondPage() {
    return true;
  }
}