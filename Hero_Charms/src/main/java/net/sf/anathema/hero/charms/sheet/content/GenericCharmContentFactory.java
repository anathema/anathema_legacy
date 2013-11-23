package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

@ForReportContent(content = GenericCharmContent.class)
public class GenericCharmContentFactory implements ReportContentFactory<GenericCharmContent> {

  private Resources resources;

  public GenericCharmContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public GenericCharmContent create(ReportSession session) {
    return new GenericCharmContent(resources, session.getHero());
  }
}
