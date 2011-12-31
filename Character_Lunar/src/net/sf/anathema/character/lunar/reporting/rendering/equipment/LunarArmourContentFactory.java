package net.sf.anathema.character.lunar.reporting.rendering.equipment;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class LunarArmourContentFactory implements ReportContentFactory<LunarArmourContent> {

  private IResources resources;

  public LunarArmourContentFactory(IResources resources)  {
    this.resources = resources;
  }

  @Override
  public LunarArmourContent create(IGenericCharacter character, IGenericDescription description) {
    return new LunarArmourContent(resources, character);
  }
}
