package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class GenericCharmContentFactory implements ReportContentFactory<GenericCharmContent> {

  private IResources resources;

  public GenericCharmContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public GenericCharmContent create(IGenericCharacter character, IGenericDescription description) {
    return new GenericCharmContent(resources, character);
  }
}
