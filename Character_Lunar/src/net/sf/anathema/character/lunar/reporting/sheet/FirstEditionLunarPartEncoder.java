package net.sf.anathema.character.lunar.reporting.sheet;

import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.common.pageformat.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionLunarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  private final SimpleEncodingRegistry registry;

  public FirstEditionLunarPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.registry = registry;
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new FirstEditionLunarGreatCurseEncoder(getBaseFont(), getSymbolBaseFont(), getResources());
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new LunarAnimaEncoderFactory(getResources(), getBaseFont(), getSymbolBaseFont()).createAnimaEncoder();
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new FirstEditionLunarBeastformPageEncoder(
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
