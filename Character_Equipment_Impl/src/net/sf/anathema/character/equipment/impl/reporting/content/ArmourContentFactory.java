package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class ArmourContentFactory implements ReportContentFactory<ArmourContent> {

  private IResources resources;

  public ArmourContentFactory(IResources resources){
    this.resources = resources;
  }

  @Override
  public ArmourContent create(IGenericCharacter character, IGenericDescription description) {
    return new ArmourContent(resources, character);
  }
}
