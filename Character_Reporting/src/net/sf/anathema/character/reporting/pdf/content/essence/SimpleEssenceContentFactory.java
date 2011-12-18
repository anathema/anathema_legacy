package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.IReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class SimpleEssenceContentFactory implements IReportContentFactory<SimpleEssenceContent>{

  private IResources resources;

  public SimpleEssenceContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public SimpleEssenceContent create(IGenericCharacter character, IGenericDescription description) {
    return new SimpleEssenceContent(resources, character);
  }
}
