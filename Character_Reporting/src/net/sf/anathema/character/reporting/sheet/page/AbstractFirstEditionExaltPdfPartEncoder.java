package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEssenceEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractFirstEditionExaltPdfPartEncoder extends AbstractFirstEditionPartEncoder {

  private final int essenceMax;
  private final BaseFont symbolBaseFont;

  public AbstractFirstEditionExaltPdfPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont());
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