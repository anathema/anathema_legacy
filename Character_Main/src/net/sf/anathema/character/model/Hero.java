package net.sf.anathema.character.model;

import net.sf.anathema.lib.util.Identified;

public interface Hero {

  <M extends CharacterModel> M getModel(Identified id);
}
