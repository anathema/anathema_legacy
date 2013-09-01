package net.sf.anathema.hero.sheet.pdf.page;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.framework.environment.Resources;

public class NullPageFactory implements PageFactory {

  @Override
  public PageEncoder[] create(EncoderRegistry encoderRegistry, Resources resources, PageSize pageSize) {
    return new PageEncoder[0];
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
