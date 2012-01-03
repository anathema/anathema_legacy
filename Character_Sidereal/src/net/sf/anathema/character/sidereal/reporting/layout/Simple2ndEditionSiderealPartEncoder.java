package net.sf.anathema.character.sidereal.reporting.layout;

import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.rendering.SiderealFlawedFateEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.SiderealAnimaEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SMALLER_FONT_SIZE;

public class Simple2ndEditionSiderealPartEncoder extends AbstractSimplePartEncoder {

  public Simple2ndEditionSiderealPartEncoder(IResources resources) {
    super(resources);
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new SiderealFlawedFateEncoder(getResources());
  }

  @Override
  public PageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new PageEncoder[] { new Simple2ndEditionSiderealDetailsPageEncoder(getResources(), SMALLER_FONT_SIZE, configuration) };
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
