package net.sf.anathema.character.lunar.reporting.layout;

import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageFactory;
import net.sf.anathema.character.reporting.pdf.rendering.pages.RegisteredAdditionalPage;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.resources.IResources;

@RegisteredAdditionalPage
public class LunarAdditionalPageFactory implements PageFactory {

  @Override
  public PageEncoder[] create(EncoderRegistry encoderRegistry, IResources resources, PageSize pageSize) {
    return new PageEncoder[] { new LunarAdditionalPageEncoder(encoderRegistry, resources, pageSize) };
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.getAdditionalModel(BeastformTemplate.TEMPLATE_ID) != null;
  }
}
