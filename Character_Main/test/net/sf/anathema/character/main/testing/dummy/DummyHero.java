package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DummyHero implements Hero {

  public final Map<Identifier, HeroModel> modelsById = new HashMap<>();


  public void addModel(HeroModel model) {
    modelsById.put(model.getId(), model);
  }

  @Override
  public <M extends HeroModel> M getModel(Identifier id) {
    return (M) modelsById.get(id);
  }

  @Override
  public boolean isFullyLoaded() {
    return true;
  }
}
