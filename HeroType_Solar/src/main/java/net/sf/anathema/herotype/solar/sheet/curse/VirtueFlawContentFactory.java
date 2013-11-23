package net.sf.anathema.herotype.solar.sheet.curse;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@Produces(VirtueFlawContent.class)
public class VirtueFlawContentFactory implements ReportContentFactory<VirtueFlawContent> {

  private Resources resources;

  public VirtueFlawContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueFlawContent create(ReportSession session) {
    return new VirtueFlawContent(session.getHero(), resources);
  }
}
