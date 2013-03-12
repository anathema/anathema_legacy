package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.lib.resources.IResources;

@CharacterModule
public class CharacterReportingModule extends CharacterModuleAdapter<CharacterReportingModuleObject> {

  private CharacterReportingModuleObject moduleObject;

  @Override
  public void initModuleObject(ICharacterGenerics characterGenerics, IResources resources) {
    this.moduleObject = new CharacterReportingModuleObject(characterGenerics.getInstantiater(), resources);
  }

  @Override
  public CharacterReportingModuleObject getModuleObject() {
    return moduleObject;
  }
}