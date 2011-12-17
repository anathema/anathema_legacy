package net.sf.anathema.character.infernal.reporting.extended;

import net.sf.anathema.character.reporting.extended.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.encoder.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.page.AbstractSecondEditionExaltPdfPartEncoder;
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
    return new InfernalAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
  
  public IPdfVariableContentBoxEncoder[] getAdditionalFirstPageEncoders()
  {
	return new IPdfVariableContentBoxEncoder[]
	         { new InfernalYoziListEncoder(getBaseFont(), getResources())};
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