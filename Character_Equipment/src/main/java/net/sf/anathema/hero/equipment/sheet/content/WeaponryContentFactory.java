package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@ForReportContent(content = WeaponryContent.class)
public class WeaponryContentFactory implements ReportContentFactory<WeaponryContent> {

  private Resources resources;

  public WeaponryContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public WeaponryContent create(ReportSession session) {
    return new WeaponryContent(session.getHero(), resources);
  }
}
