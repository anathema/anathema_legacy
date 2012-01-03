package net.sf.anathema.character.solar.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.SubContent;
import net.sf.anathema.lib.resources.IResources;

public class VirtueFlawContentFactory implements ReportContentFactory {

  private IResources resources;

  public VirtueFlawContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public SubContent create(IGenericCharacter character, IGenericDescription description) {
    return new VirtueFlawContent(resources, character);
  }
}
