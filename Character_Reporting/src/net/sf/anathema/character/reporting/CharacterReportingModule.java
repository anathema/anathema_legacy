package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.resources.IResources;

@CharacterModule
public class CharacterReportingModule extends CharacterModuleAdapter {

  @Override
  public void initModuleObject(ICharacterGenerics characterGenerics, IResources resources) {
    Instantiater instantiater = characterGenerics.getInstantiater();
    CharacterReportingModuleObject moduleObject = new CharacterReportingModuleObject(instantiater, resources);
    characterGenerics.getModuleObjectMap().addModule(this, moduleObject);
  }
}