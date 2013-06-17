package net.sf.anathema.character.main.model;

import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DefaultHero implements Hero {

  private Map<String, HeroModel> modelsById = new HashMap<>();
  private boolean fullyLoaded = false;

  public void addModel(HeroModel model) {
    modelsById.put(model.getId().getId(), model);
  }

  @Override
  public <M extends HeroModel> M getModel(Identifier id) {
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
