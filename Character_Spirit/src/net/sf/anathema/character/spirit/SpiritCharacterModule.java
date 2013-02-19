package net.sf.anathema.character.spirit;

import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.type.ICharacterType;

@CharacterModule
public class SpiritCharacterModule extends CharacterTypeModule {

  @Override
  protected ICharacterType getType() {
	  return new SpiritCharacterType();
  }
}