package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = ArmourContent.class)
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
