package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DefaultHero implements Hero {

  private CharacterListening listening = new CharacterListening(this);
  private Map<String, HeroModel> modelsById = new HashMap<>();
  private boolean fullyLoaded = false;
  private final HeroTemplate template;

  public DefaultHero(HeroTemplate template) {
    this.template = template;
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
    return (M) modelsById.get(id.getId());
  }

  @Override
  public boolean isFullyLoaded() {
    return fullyLoaded;
  }

  public void setFullyLoaded(boolean fullyLoaded) {
    this.fullyLoaded = fullyLoaded;
  }

  public void addModel(HeroModel model) {
    modelsById.put(model.getId().getId(), model);
  }

  public CharacterListening getListening() {
    return listening;
  }
}
