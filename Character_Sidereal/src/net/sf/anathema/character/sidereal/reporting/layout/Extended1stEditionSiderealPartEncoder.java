package net.sf.anathema.character.sidereal.reporting.layout;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.rendering.SiderealParadoxEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.SiderealAnimaEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionSiderealPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Extended1stEditionSiderealPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new SiderealParadoxEncoder(getResources());
  }

  @Override
  public PageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new PageEncoder[] { new Extended1stEditionSiderealDetailsPageEncoder(getResources(), getBaseFont(), getFontSize(), configuration) };
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
