package net.sf.anathema.character.sidereal.reporting.extended;

import net.sf.anathema.character.reporting.extended.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.extended.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pageformat.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionSiderealPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public FirstEditionSiderealPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);

  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new SiderealParadoxEncoder(getBaseFont(), getSymbolBaseFont(), getResources());
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new FirstEditionSiderealDetailsPageEncoder(
        getResources(),
        getEssenceMax(),
        getBaseFont(),
        getSymbolBaseFont(),
        getFontSize(),
        configuration) };
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
}