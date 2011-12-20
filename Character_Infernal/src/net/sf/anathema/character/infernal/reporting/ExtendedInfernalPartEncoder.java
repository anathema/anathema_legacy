package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedInfernalPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  //private final PdfEncodingRegistry registry;

  public ExtendedInfernalPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    //this.registry = registry;
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new InfernalUrgeEncoder(getResources(), getBaseFont());
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new InfernalAnimaEncoderFactory(getResources(), getBaseFont(), getBaseFont()).createAnimaEncoder();
  }
  
  public IVariableBoxContentEncoder[] getAdditionalFirstPageEncoders()
  {
	return new IVariableBoxContentEncoder[]
	         { new InfernalYoziListEncoder()};
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
