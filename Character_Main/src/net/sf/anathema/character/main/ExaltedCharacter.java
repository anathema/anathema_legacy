package net.sf.anathema.character.main;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.initialization.HeroModelInitializer;
import net.sf.anathema.hero.model.DefaultHero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.ModelInitializationContext;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

import java.lang.*;

public class ExaltedCharacter implements Character {

  private final CharacterChangeManagement management = new CharacterChangeManagement(this);
  private final DefaultHero hero;
  private final ModelInitializationContext initializationContext;

  public ExaltedCharacter(HeroTemplate template, HeroEnvironment generics) {
    this.hero = new DefaultHero(template);
    this.initializationContext = new ModelInitializationContext(generics);
    addModels(generics);
    management.initListening();
  }

  private void addModels(HeroEnvironment generics) {
    HeroModelInitializer initializer = new HeroModelInitializer(initializationContext, getTemplate());
    initializer.addModels(generics, hero);
  }

  // todo (sandra): remove itemDate-Relicts in Character (see ExaltedCharacterPersister)
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    HeroDescription characterDescription = HeroDescriptionFetcher.fetch(this);
    if (characterDescription == null) {
      return;
    }
    ITextualDescription characterName = characterDescription.getName();
    characterName.addTextChangedListener(adjuster);
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

  public void setFullyLoaded(boolean loaded) {
    hero.setFullyLoaded(loaded);
  }

  @Override
  public boolean isFullyLoaded() {
    return hero.isFullyLoaded();
  }

}