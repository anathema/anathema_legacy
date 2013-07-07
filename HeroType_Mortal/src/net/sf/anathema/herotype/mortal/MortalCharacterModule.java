package net.sf.anathema.herotype.mortal;

import net.sf.anathema.character.main.framework.module.CharacterModule;
import net.sf.anathema.character.main.framework.module.CharacterTypeModule;
import net.sf.anathema.character.main.type.ICharacterType;

@CharacterModule
public class MortalCharacterModule extends CharacterTypeModule {

  @Override
  protected ICharacterType getType() {
	  return new MortalCharacterType();
  }
}