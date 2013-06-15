package net.sf.anathema.character.model;

import net.sf.anathema.lib.util.Identified;

public interface CharacterModelFactory {

  Identified getModelId();

  <M extends CharacterModel> M create(TemplateFactory templateFactory);
}
