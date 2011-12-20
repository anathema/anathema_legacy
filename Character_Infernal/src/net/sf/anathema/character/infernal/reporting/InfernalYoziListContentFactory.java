package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class InfernalYoziListContentFactory implements ReportContentFactory<InfernalYoziListContent>{

  private IResources resources;

  public InfernalYoziListContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public InfernalYoziListContent create(IGenericCharacter character, IGenericDescription description) {
    return new InfernalYoziListContent(character, resources);
  }
}
