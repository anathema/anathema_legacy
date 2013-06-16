package net.sf.anathema.character.main.model;

import net.sf.anathema.lib.util.Identifier;

public interface CharacterModel {

  Identifier getId();

  void initialize(InitializationContext context, Hero hero);
}
