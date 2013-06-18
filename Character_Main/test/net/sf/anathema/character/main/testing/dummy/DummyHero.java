package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.testing.dummy.template.DummyHeroTemplate;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DummyHero implements Hero {

  public final DummyHeroTemplate template = new DummyHeroTemplate();
  public final CharacterListening listening = new CharacterListening(this);
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
    return listening;
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
