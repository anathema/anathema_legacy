package net.sf.anathema.character.model;

import net.sf.anathema.lib.util.Identifier;

public interface CharacterModelFactory {

  Identifier getModelId();

  <M extends CharacterModel> M create(TemplateFactory templateFactory);
}
