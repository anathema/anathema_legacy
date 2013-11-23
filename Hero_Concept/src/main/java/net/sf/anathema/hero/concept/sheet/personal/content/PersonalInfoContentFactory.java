package net.sf.anathema.hero.concept.sheet.personal.content;

import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@ForReportContent(content = PersonalInfoContent.class)
public class PersonalInfoContentFactory implements ReportContentFactory<PersonalInfoContent> {

  private Resources resources;

  public PersonalInfoContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public PersonalInfoContent create(ReportSession session) {
    return new PersonalInfoContent(resources, session.getHero());
  }
}
