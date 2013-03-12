package net.sf.anathema.character.generic.framework.module.object;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.framework.module.ICharacterModule;

import java.util.HashMap;
import java.util.Map;

public class CharacterModuleObjectMap implements ICharacterModuleObjectMap {
  private final Map<Class<? extends ICharacterModule<?>>, ICharacterModuleObject> moduleObjects = new HashMap<>();

  @Override
  @SuppressWarnings("unchecked")
  public <T extends ICharacterModuleObject> T getModuleObject(Class<? extends ICharacterModule<T>> moduleClass) {
    Preconditions.checkArgument(ICharacterModule.class.isAssignableFrom(moduleClass), "Must implement ICharacterModule");
    return (T) moduleObjects.get(moduleClass);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends ICharacterModuleObject> void addModule(ICharacterModule<T> module, T moduleObject) {
      moduleObjects.put(module.getClass(), moduleObject);
  }
}