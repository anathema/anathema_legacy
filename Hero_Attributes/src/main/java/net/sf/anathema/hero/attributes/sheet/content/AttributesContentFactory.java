package net.sf.anathema.hero.attributes.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@RegisteredReportContent(produces = AttributesContent.class)
public class AttributesContentFactory implements ReportContentFactory<AttributesContent> {
  private Resources resources;

  public AttributesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public AttributesContent create(ReportSession session) {
    return new AttributesContent(session.getHero(), resources);
  }
}
