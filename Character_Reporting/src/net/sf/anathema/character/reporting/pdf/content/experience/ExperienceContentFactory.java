package net.sf.anathema.character.reporting.pdf.content.experience;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class ExperienceContentFactory implements ReportContentFactory<ExperienceContent> {

  private IResources resources;

  public ExperienceContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public ExperienceContent create(IGenericCharacter character, IGenericDescription description) {
    return new ExperienceContent(resources, character);
  }
}
