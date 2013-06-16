package net.sf.anathema.character.main.model;

import net.sf.anathema.character.main.model.CharacterModel;
import net.sf.anathema.lib.util.Identifier;

public interface Hero {

  <M extends CharacterModel> M getModel(Identifier id);
}
