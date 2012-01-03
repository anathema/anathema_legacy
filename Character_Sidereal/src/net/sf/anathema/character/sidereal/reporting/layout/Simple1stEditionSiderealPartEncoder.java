package net.sf.anathema.character.sidereal.reporting.layout;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.rendering.SiderealParadoxEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.SiderealAnimaEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionSiderealPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public Simple1stEditionSiderealPartEncoder(IResources resources) {
    super(resources);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SiderealParadoxEncoder(getResources());
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new Simple1stEditionSiderealDetailsPageEncoder(getResources(), getFontSize(), configuration) };
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
