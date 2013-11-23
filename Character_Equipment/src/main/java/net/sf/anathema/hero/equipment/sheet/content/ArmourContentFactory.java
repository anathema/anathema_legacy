package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@Produces(ArmourContent.class)
public class ArmourContentFactory implements ReportContentFactory<ArmourContent> {

  private Resources resources;

  public ArmourContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ArmourContent create(ReportSession session) {
    return new ArmourContent(session.getHero(), resources);
  }
}
