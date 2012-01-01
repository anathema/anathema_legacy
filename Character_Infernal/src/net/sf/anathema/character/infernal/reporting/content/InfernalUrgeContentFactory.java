package net.sf.anathema.character.infernal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class InfernalUrgeContentFactory implements ReportContentFactory<InfernalUrgeContent> {
  private IResources resources;

  public InfernalUrgeContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override

  public InfernalUrgeContent create(IGenericCharacter character, IGenericDescription description) {
    return new InfernalUrgeContent(resources, character);
  }
}
