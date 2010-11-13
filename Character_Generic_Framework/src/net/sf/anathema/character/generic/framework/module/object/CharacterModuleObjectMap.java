// Copyright (c) 2006 by disy Informationssysteme GmbH
package net.sf.anathema.character.generic.framework.module.object;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.module.ICharacterModule;

// NOT_PUBLISHED
public class CharacterModuleObjectMap implements ICharacterModuleObjectMap {
  private final Map<Class< ? extends ICharacterModule< ? >>, ICharacterModuleObject> moduleObjects = new HashMap<Class< ? extends ICharacterModule< ? >>, ICharacterModuleObject>();

  @SuppressWarnings("unchecked")
  public <T extends ICharacterModuleObject> T getModuleObject(Class< ? extends ICharacterModule<T>> moduleClass) {
    Ensure.ensureArgumentTrue("Must implement ICoreModule", ICharacterModule.class //$NON-NLS-1$
    .isAssignableFrom(moduleClass));
    return (T) moduleObjects.get(moduleClass);
  }

  @SuppressWarnings("unchecked")
  public <T extends ICharacterModuleObject> void addModule(ICharacterModule<T> module) {
    T moduleObject = module.getModuleObject();
    if (moduleObject != null) {
      moduleObjects.put((Class< ? extends ICharacterModule<T>>) module.getClass(), moduleObject);
    }
  }
}