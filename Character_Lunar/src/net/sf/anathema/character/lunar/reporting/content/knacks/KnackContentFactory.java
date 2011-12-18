package net.sf.anathema.character.lunar.reporting.content.knacks;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.IReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class KnackContentFactory implements IReportContentFactory<KnackContent> {

  private IResources resources;

  public KnackContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public KnackContent create(IGenericCharacter character, IGenericDescription description) {
    return new KnackContent(resources, character);
  }
}
