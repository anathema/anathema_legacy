package net.sf.anathema.character.sidereal.reporting.layout;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.character.sidereal.reporting.rendering.SiderealFlawedFateEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.anima.SiderealAnimaEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionSiderealPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Simple2ndEditionSiderealPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SiderealFlawedFateEncoder(baseFont, getResources());
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new Simple2ndEditionSiderealDetailsPageEncoder(getResources(), getEssenceMax(), baseFont, symbolBaseFont,
      IVoidStateFormatConstants.SMALLER_FONT_SIZE, configuration) };
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new SiderealAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }
}
