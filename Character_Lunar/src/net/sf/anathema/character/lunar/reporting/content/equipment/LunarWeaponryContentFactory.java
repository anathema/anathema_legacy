package net.sf.anathema.character.lunar.reporting.content.equipment;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class LunarWeaponryContentFactory implements ReportContentFactory<LunarWeaponryContent> {

  private IResources resources;

  public LunarWeaponryContentFactory(IResources resources)  {
    this.resources = resources;
  }

  @Override
  public LunarWeaponryContent create(IGenericCharacter character, IGenericDescription description) {
    return new LunarWeaponryContent(resources, character);
  }
}
