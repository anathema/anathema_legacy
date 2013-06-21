package net.sf.anathema.hero.initialization;

import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;

public class DummyModelFactory implements HeroModelFactory {
  private final Identifier id;
  private final ArrayList<Identifier> requirements = new ArrayList<>();

  public DummyModelFactory(Identifier id) {
    this.id = id;
  }

  public void addRequirement(Identifier id) {
    requirements.add(id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <M extends HeroModel> M create(TemplateFactory templateFactory) {
    return (M) new DummyHeroModel(id);
  }

  @Override
  public Iterable<Identifier> getRequiredModelIds() {
    return requirements;
  }

  @Override
  public Identifier getModelId() {
    return id;
  }
}
