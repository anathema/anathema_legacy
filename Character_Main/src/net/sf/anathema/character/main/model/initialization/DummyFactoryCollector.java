package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.Collection;

public class DummyFactoryCollector implements ModelFactoryCollector {
  @Override
  public Collection<CharacterModelFactory> collect() {
    ArrayList<CharacterModelFactory> models = new ArrayList();
    models.add(new DummyModelFactory());
    return models;
  }

  private static class DummyModelFactory implements CharacterModelFactory {
    @Override
    public <M extends CharacterModel> M create(TemplateFactory templateFactory) {
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
