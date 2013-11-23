package net.sf.anathema.hero.abilities.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@ForReportContent(content = AbilitiesContent.class)
public class AbilitiesContentFactory implements ReportContentFactory<AbilitiesContent> {
  private Resources resources;

  public AbilitiesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public AbilitiesContent create(ReportSession session) {
    return new AbilitiesContent(session.getHero(), resources);
  }
}
