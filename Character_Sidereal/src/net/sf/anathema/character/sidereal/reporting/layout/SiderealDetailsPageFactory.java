package net.sf.anathema.character.sidereal.reporting.layout;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.pages.PageFactory;
import net.sf.anathema.character.reporting.pdf.rendering.pages.RegisteredAdditionalPage;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.resources.IResources;

@RegisteredAdditionalPage
public class SiderealDetailsPageFactory implements PageFactory {

  @Override
  public PageEncoder[] create(EncoderRegistry encoderRegistry, IResources resources, PageSize pageSize) {
    return new PageEncoder[] { new SiderealDetailsPageEncoder(resources, pageSize) };
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.getAdditionalModel(SiderealCollegeTemplate.ID) != null;
  }
}
