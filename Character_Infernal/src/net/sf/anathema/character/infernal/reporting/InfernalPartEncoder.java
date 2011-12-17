package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.lib.resources.IResources;

public class InfernalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  //private final PdfEncodingRegistry registry;

  public InfernalPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    //this.registry = registry;
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new InfernalUrgeEncoder(getResources(), getBaseFont());
  }

  @Override
  public IPdfContentBoxEncoder getAnimaEncoder() {
    return new InfernalAnimaEncoderFactory(getResources(), getBaseFont(), getSymbolBaseFont()).createAnimaEncoder();
  }

  /*@Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new SecondEditionLunarAdditionalPageEncoder(
        this,
        registry,
        getResources(),
        getEssenceMax(),
        configuration) };
  }*/
}