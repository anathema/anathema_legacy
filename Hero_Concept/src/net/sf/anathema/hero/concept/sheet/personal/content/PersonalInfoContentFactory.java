package net.sf.anathema.hero.concept.sheet.personal.content;

import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.hero.experience.sheet.content.ExperienceContent;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = PersonalInfoContent.class)
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
