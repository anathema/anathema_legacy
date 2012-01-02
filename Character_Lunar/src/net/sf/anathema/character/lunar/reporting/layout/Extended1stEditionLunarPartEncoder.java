package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.lunar.reporting.rendering.anima.LunarAnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.FirstEditionLunarGreatCurseEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionLunarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  private BaseFont baseFont;

  public Extended1stEditionLunarPartEncoder(IResources resources, BaseFont baseFont, int essenceMax) {
    super(resources, baseFont, essenceMax);
    this.baseFont = baseFont;
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
    return new IPdfPageEncoder[] { new Extended1stEditionLunarBeastformPageEncoder(this, baseFont, getResources(), getEssenceMax(), configuration) };
  }

  @Override
  public boolean isEncodeAttributeAsFavorable() {
    return true;
  }
}
