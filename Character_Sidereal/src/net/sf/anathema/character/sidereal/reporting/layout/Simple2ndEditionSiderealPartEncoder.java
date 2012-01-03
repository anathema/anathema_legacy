package net.sf.anathema.character.sidereal.reporting.layout;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.rendering.SiderealFlawedFateEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.SiderealAnimaEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionSiderealPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  public Simple2ndEditionSiderealPartEncoder(IResources resources) {
    super(resources);
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SiderealFlawedFateEncoder(getResources());
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new Simple2ndEditionSiderealDetailsPageEncoder(getResources(), IVoidStateFormatConstants.SMALLER_FONT_SIZE,
      configuration) };
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
