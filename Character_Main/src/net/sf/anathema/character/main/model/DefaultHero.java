package net.sf.anathema.character.main.model;

import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DefaultHero implements Hero {

  private Map<String, CharacterModel> modelsById = new HashMap<>();
  private boolean fullyLoaded = false;

  public void addModel(CharacterModel model) {
    modelsById.put(model.getId().getId(), model);
  }

  @Override
  public <M extends CharacterModel> M getModel(Identifier id) {
    return (M) modelsById.get(id.getId());
  }

  @Override
  public boolean isFullyLoaded() {
    return fullyLoaded;
  }

  public void setFullyLoaded(boolean fullyLoaded) {
    this.fullyLoaded = fullyLoaded;
  }
}
