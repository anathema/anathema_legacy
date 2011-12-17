package net.sf.anathema.character.lunar.reporting.sheet;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.common.pageformat.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionLunarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private final PdfEncodingRegistry registry;

  public SecondEditionLunarPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.registry = registry;
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new SecondEditionLunarGreatCurseEncoder(getBaseFont());
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new LunarAnimaEncoderFactory(getResources(), getBaseFont(), getSymbolBaseFont()).createAnimaEncoder();
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new SecondEditionLunarAdditionalPageEncoder(
        this,
        registry,
        getResources(),
        getEssenceMax(),
        configuration) };
  }

  @Override
  public boolean isEncodeAttributeAsFavorable() {
    return true;
  }
}
