package net.sf.anathema.character.main.model;

import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DefaultHero implements Hero {

  private Map<String, CharacterModel> modelsById = new HashMap<>();

  public void addModel(CharacterModel model) {
    modelsById.put(model.getId().getId(), model);
  }

  @Override
  public <M extends CharacterModel> M getModel(Identifier id) {
    return (M) modelsById.get(id.getId());
  }
}
