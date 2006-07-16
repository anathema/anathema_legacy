package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEssenceEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractSecondEditionExaltPdfPartEncoder extends AbstractSecondEditionPartEncoder {

  private final int essenceMax;
  private final BaseFont symbolBaseFont;

  public AbstractSecondEditionExaltPdfPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), registry.getSymbolBaseFont());
    this.symbolBaseFont = registry.getSymbolBaseFont();
    this.essenceMax = essenceMax;
  }

  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new PdfAnimaEncoder(getResources(), getBaseFont(), symbolBaseFont);
  }

  public IPdfContentBoxEncoder getEssenceEncoder() {
    return new PdfEssenceEncoder(getBaseFont(), getResources(), essenceMax);
  }

  public boolean hasSecondPage() {
    return true;
  }
}