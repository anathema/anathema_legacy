package net.sf.anathema.hero.spiritual.sheet.essence.content;

import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@ForReportContent(content = SimpleEssenceContent.class)
public class SimpleEssenceContentFactory implements ReportContentFactory<SimpleEssenceContent> {

  private Resources resources;

  public SimpleEssenceContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public SimpleEssenceContent create(ReportSession session) {
    return new SimpleEssenceContent(resources, session.getHero());
  }
}
