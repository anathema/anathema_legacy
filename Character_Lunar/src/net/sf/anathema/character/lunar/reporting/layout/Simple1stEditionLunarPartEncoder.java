package net.sf.anathema.character.lunar.reporting.layout;

import net.sf.anathema.character.lunar.reporting.rendering.anima.LunarAnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.FirstEditionLunarGreatCurseEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class Simple1stEditionLunarPartEncoder extends AbstractSimplePartEncoder {

  private EncoderRegistry encoderRegistry;

  public Simple1stEditionLunarPartEncoder(EncoderRegistry encoderRegistry, IResources resources) {
    super(resources);
    this.encoderRegistry = encoderRegistry;
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
    return new PageEncoder[] { new Simple1stEditionLunarBeastformPageEncoder(encoderRegistry, this, getResources(), configuration) };
  }
}
