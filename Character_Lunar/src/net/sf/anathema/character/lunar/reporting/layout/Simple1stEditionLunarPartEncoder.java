package net.sf.anathema.character.lunar.reporting.layout;

import net.sf.anathema.character.lunar.reporting.rendering.anima.LunarAnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.FirstEditionLunarGreatCurseEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionLunarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Simple1stEditionLunarPartEncoder(IResources resources) {
    super(resources);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new FirstEditionLunarGreatCurseEncoder(getResources());
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new LunarAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new Simple1stEditionLunarBeastformPageEncoder(this, getResources(), configuration) };
  }

  @Override
  public boolean isEncodeAttributeAsFavorable() {
    return true;
  }
}
