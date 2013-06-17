package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.main.model.HeroModel;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.character.main.model.template.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.Collection;

public class DummyFactoryCollector implements ModelFactoryCollector {
  @Override
  public Collection<HeroModelFactory> collect() {
    ArrayList<HeroModelFactory> models = new ArrayList<>();
    models.add(new DummyModelFactory());
    return models;
  }

  private static class DummyModelFactory implements HeroModelFactory {
    @Override
    public <M extends HeroModel> M create(TemplateFactory templateFactory) {
      return null;
    }

    @Override
    public Iterable<Identifier> getRequiredModelIds() {
      ArrayList<Identifier> requirements = new ArrayList<>();
      requirements.add(new SimpleIdentifier("X"));
      requirements.add(new SimpleIdentifier("Y"));
      return requirements;
    }

    @Override
    public Identifier getModelId() {
      return new SimpleIdentifier("A");
    }
  }
}
