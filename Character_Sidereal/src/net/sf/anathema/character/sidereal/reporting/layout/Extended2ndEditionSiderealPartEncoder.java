package net.sf.anathema.character.sidereal.reporting.layout;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.rendering.SiderealFlawedFateEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.SiderealAnimaEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionSiderealPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Extended2ndEditionSiderealPartEncoder(IResources resources, ExtendedEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SiderealFlawedFateEncoder(baseFont, getResources());
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new Extended2ndEditionSiderealDetailsPageEncoder(getResources(), baseFont, getFontSize(), configuration) };
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }
}
