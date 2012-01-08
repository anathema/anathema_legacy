package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.object.NullCharacterModuleObject;

public abstract class NullObjectCharacterModuleAdapter extends CharacterModuleAdapter<NullCharacterModuleObject> {

  public final void initModuleObject(ICharacterGenerics characterGenerics) {
    // nothing to do
  }

  public final NullCharacterModuleObject getModuleObject() {
    return null;
  }
}
