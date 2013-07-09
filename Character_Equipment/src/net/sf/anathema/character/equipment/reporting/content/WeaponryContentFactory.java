package net.sf.anathema.character.equipment.reporting.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = WeaponryContent.class)
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
