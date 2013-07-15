package net.sf.anathema.hero.initialization;

import net.sf.anathema.hero.model.HeroModelFactory;

import java.util.ArrayList;
import java.util.Collection;

public class DummyFactoryCollector implements ModelFactoryCollector {
  private final ArrayList<HeroModelFactory> models = new ArrayList<>();

  public void addFactory(HeroModelFactory modelFactory){
    models.add(modelFactory);
  }

  @Override
  public Collection<HeroModelFactory> collect() {
    return models;
  }

}
