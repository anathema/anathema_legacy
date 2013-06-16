package net.sf.anathema.character.main.model;

import net.sf.anathema.character.main.model.initialization.ModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;

public interface CharacterModelFactory extends ModelTreeEntry {

  <M extends CharacterModel> M create(TemplateFactory templateFactory);
}
