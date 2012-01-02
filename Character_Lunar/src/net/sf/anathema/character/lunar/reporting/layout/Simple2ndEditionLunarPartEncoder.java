package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.lunar.reporting.rendering.anima.LunarAnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.SecondEditionLunarGreatCurseEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSecondEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionLunarPartEncoder extends AbstractSecondEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Simple2ndEditionLunarPartEncoder(IResources resources, SimpleEncodingRegistry registry, int essenceMax) {
    super(resources, registry.getBaseFont(), essenceMax);
    this.baseFont = registry.getBaseFont();
  }

  public IBoxContentEncoder getGreatCurseEncoder() {
    return new SecondEditionLunarGreatCurseEncoder(baseFont);
  }

  @Override
  public IBoxContentEncoder getAnimaEncoder() {
    return new LunarAnimaEncoderFactory(getResources(), baseFont).createAnimaEncoder();
  }

  @Override
  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new IPdfPageEncoder[] { new Lunar2ndEditionAdditionalPageEncoder(this, baseFont, getResources(), configuration) };
  }

  @Override
  public boolean isEncodeAttributeAsFavorable() {
    return true;
  }
}
