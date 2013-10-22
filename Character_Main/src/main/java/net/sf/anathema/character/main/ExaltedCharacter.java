package net.sf.anathema.character.main;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.initialization.HeroModelInitializer;
import net.sf.anathema.hero.model.DefaultHero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

import java.util.Iterator;

public class ExaltedCharacter implements Character {

  private final CharacterChangeManagement management = new CharacterChangeManagement(this);
  private final DefaultHero hero;

  public ExaltedCharacter(HeroTemplate template, HeroEnvironment environment) {
    this.hero = new DefaultHero(template);
    addModels(environment);
    management.initListening();
  }

  private void addModels(HeroEnvironment environment) {
    HeroModelInitializer initializer = new HeroModelInitializer(environment, getTemplate());
    initializer.addModels(hero);
  }

  @Override
  public ChangeManagement getChangeManagement() {
    return management;
  }

  @Override
  public HeroTemplate getTemplate() {
    return hero.getTemplate();
  }

  @Override
  public ChangeAnnouncer getChangeAnnouncer() {
    return hero.getChangeAnnouncer();
  }

  @Override
  public <M extends HeroModel> M getModel(Identifier id) {
    return hero.getModel(id);
  }

  public void markReadyForWork() {
    hero.setFullyLoaded(true);
  }

  @Override
  public boolean isFullyLoaded() {
    return hero.isFullyLoaded();
  }

  @Override
  public Iterator<HeroModel> iterator() {
    return hero.iterator();
  }
}