package net.sf.anathema.character.generic.framework.module.object;

import net.sf.anathema.character.generic.framework.module.ICharacterModule;

public interface ICharacterModuleObjectMap {

  <T extends ICharacterModuleObject> T getModuleObject(Class<? extends ICharacterModule> moduleClass);

  void addModule(ICharacterModule module, ICharacterModuleObject moduleObject);
}