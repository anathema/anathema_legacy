package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = GenericCharmContent.class)
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
