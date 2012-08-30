package net.sf.anathema.character.mortal;

import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.type.CharacterType;

@CharacterModule
public class MortalCharacterModule extends CharacterTypeModule {

  @Override
  protected CharacterType getType() {
	  return CharacterType.MORTAL;
  }
}