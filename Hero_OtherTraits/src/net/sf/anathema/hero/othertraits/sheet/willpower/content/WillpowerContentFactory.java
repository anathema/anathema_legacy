package net.sf.anathema.hero.othertraits.sheet.willpower.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = WillpowerContent.class)
public class WillpowerContentFactory implements ReportContentFactory<WillpowerContent> {

  private final Resources resources;

  public WillpowerContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public WillpowerContent create(ReportSession session) {
    return new WillpowerContent(resources, session.getHero());
  }
}
