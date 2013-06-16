package net.sf.anathema.character.model;

import net.sf.anathema.character.main.lib.ModelTreeEntry;

public interface CharacterModelFactory extends ModelTreeEntry {

  <M extends CharacterModel> M create(TemplateFactory templateFactory);
}
