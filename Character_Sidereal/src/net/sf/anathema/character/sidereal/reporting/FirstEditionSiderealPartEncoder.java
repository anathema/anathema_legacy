package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionSiderealPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public FirstEditionSiderealPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);

  }

  @Override
  protected int getAnimaPowerCount() {
    return 3;
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new SiderealAnimaTableEncoder(getResources(), getBaseFont(), getFontSize());
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new SiderealParadoxEncoder(getBaseFont(), getSymbolBaseFont(), getResources());
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new SiderealDetailsPageEncoder(
        getResources(),
        getEssenceMax(),
        getBaseFont(),
        getSymbolBaseFont(),
        getFontSize(),
        configuration) };
  }
}