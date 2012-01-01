package net.sf.anathema.character.ghost.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class GhostPassionContentFactory implements ReportContentFactory<GhostPassionContent> {
  private IResources resources;

  public GhostPassionContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override

  public GhostPassionContent create(IGenericCharacter character, IGenericDescription description) {
    return new GhostPassionContent(resources, character);
  }
}
