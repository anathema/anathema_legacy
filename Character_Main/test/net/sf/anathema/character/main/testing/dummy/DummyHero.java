package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.testing.dummy.models.DummyTraitModel;
import net.sf.anathema.character.main.testing.dummy.template.DummyHeroTemplate;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeAnnouncerImpl;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DummyHero implements Hero {

  private final ChangeAnnouncer changeAnnouncer = new ChangeAnnouncerImpl();

  public static DummyHero createWithTraits(Trait... traits) {
    DummyHero hero = new DummyHero();
    DummyTraitModel traitModel = new DummyTraitModel();
    hero.addModel(traitModel);
    traitModel.addTraits(traits);
    return hero;
  }

  public final DummyHeroTemplate template = new DummyHeroTemplate();
  public final Map<Identifier, HeroModel> modelsById = new HashMap<>();


  public void addModel(HeroModel model) {
    modelsById.put(model.getId(), model);
  }

  @Override
  public HeroTemplate getTemplate() {
    return template;
  }

  @Override
  public ChangeAnnouncer getChangeAnnouncer() {
    return changeAnnouncer;
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
