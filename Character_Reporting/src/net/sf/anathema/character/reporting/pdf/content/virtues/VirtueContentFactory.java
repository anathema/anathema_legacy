package net.sf.anathema.character.reporting.pdf.content.virtues;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.IReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class VirtueContentFactory implements IReportContentFactory<VirtueContent> {

  private final IResources resources;

  public VirtueContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueContent create(IGenericCharacter character, IGenericDescription description) {
    return new VirtueContent(resources, character);
  }
}
