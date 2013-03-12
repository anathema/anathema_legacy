package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.NullCharacterModuleObject;
import net.sf.anathema.lib.resources.IResources;

public abstract class NullObjectCharacterModuleAdapter extends CharacterModuleAdapter<NullCharacterModuleObject> {

  @Override
  public final void initModuleObject(ICharacterGenerics characterGenerics, IResources resources) {
    // nothing to do
  }

  @Override
  public final NullCharacterModuleObject getModuleObject() {
    return null;
  }
}
