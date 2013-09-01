package net.sf.anathema.hero.spiritual.sheet.virtues.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@RegisteredReportContent(produces = VirtueContent.class)
public class VirtueContentFactory implements ReportContentFactory<VirtueContent> {

  private final Resources resources;

  public VirtueContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueContent create(ReportSession session) {
    return new VirtueContent(resources, session.getHero());
  }
}
