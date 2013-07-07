package net.sf.anathema.herotype.mortal;

import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.type.ICharacterType;

@CharacterModule
public class MortalCharacterModule extends CharacterTypeModule {

  @Override
  protected ICharacterType getType() {
	  return new MortalCharacterType();
  }
}