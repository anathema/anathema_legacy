package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.lunar.reporting.rendering.anima.LunarAnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.FirstEditionLunarGreatCurseEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class Extended1stEditionLunarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  private EncoderRegistry encoderRegistry;
  private BaseFont baseFont;

  public Extended1stEditionLunarPartEncoder(EncoderRegistry encoderRegistry, IResources resources, BaseFont baseFont, int essenceMax) {
    super(resources, baseFont, essenceMax);
    this.encoderRegistry = encoderRegistry;
    this.baseFont = baseFont;
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new FirstEditionLunarGreatCurseEncoder(getResources());
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new LunarAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }

  @Override
  public PageEncoder[] getAdditionalPages(PdfPageConfiguration configuration) {
    return new PageEncoder[] { new Extended1stEditionLunarBeastformPageEncoder(encoderRegistry, this, baseFont, getResources(), getEssenceMax(),
      configuration) };
  }
}
