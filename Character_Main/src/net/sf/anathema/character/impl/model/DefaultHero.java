package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.Hero;

import java.util.HashMap;
import java.util.Map;

public class DefaultHero implements Hero {

  private Map<String, CharacterModel> modelsById = new HashMap<>();

  public void addModel(CharacterModel model) {
    modelsById.put(model.getId().getId(), model);
  }

  @Override
  public CharacterModel getModel(String id) {
    return modelsById.get(id);
  }
}
