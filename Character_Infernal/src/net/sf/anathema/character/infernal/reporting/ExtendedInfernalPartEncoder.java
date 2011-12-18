package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.infernal.reporting.InfernalYoziListEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfVariableContentBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedInfernalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  //private final PdfEncodingRegistry registry;

  public ExtendedInfernalPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
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
