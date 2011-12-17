package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionSiderealPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public SecondEditionSiderealPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);

  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
      return new SiderealFlawedFateEncoder(getBaseFont(), getSymbolBaseFont(), getResources());
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new SecondEditionSiderealDetailsPageEncoder(
        getResources(),
        getEssenceMax(),
        getBaseFont(),
        getSymbolBaseFont(),
        getFontSize(),
        configuration) };
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources(), getBaseFont(), getSymbolBaseFont()).createAnimaEncoder();
  }
}
