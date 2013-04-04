package net.sf.anathema.character.reporting.pdf.content.experience;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = ExperienceContent.class)
public class ExperienceContentFactory implements ReportContentFactory<ExperienceContent> {

  private Resources resources;

  public ExperienceContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ExperienceContent create(ReportSession session, IGenericCharacter character) {
    return new ExperienceContent(resources, character);
  }
}
