package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.lib.resources.IResources;

@CharacterModule
public class CharacterReportingModule extends CharacterModuleAdapter<CharacterReportingModuleObject> {

  private CharacterReportingModuleObject moduleObject;

  @Override
  public void initModuleObject(ICharacterGenerics characterGenerics) {
    this.moduleObject = new CharacterReportingModuleObject(characterGenerics.getInstantiater());
  }

  @Override
  public CharacterReportingModuleObject getModuleObject() {
    return moduleObject;
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    moduleObject.getContentRegistry().setResources(resources);
    moduleObject.getExtendedEncodingRegistry().setResources(resources);
  }
}