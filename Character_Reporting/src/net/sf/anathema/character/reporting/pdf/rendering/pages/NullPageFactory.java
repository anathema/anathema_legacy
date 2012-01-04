package net.sf.anathema.character.reporting.pdf.rendering.pages;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class NullPageFactory implements PageFactory {

  @Override
  public PageEncoder[] create(EncoderRegistry encoderRegistry, IResources resources, PageConfiguration configuration) {
    return new PageEncoder[0];
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
