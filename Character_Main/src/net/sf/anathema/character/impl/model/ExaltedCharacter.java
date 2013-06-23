package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.main.model.description.HeroDescriptionFetcher;
import net.sf.anathema.character.model.Character;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.initialization.HeroModelInitializer;
import net.sf.anathema.hero.model.DefaultHero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.ModelInitializationContext;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class ExaltedCharacter implements Character {

  private final CharacterChangeManagement management = new CharacterChangeManagement(this);
  private final DefaultHero hero;
  private final ModelInitializationContext initializationContext;

  public ExaltedCharacter(HeroTemplate template, ICharacterGenerics generics) {
    this.hero = new DefaultHero(template);
    this.initializationContext = new ModelInitializationContext(generics);
    addModels(generics);
    management.initListening();
  }

  private void addModels(ICharacterGenerics generics) {
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
  public void addDirtyListener(IChangeListener changeListener) {
    management.addDirtyListener(changeListener);
  }

  @Override
  public boolean isDirty() {
    return management.isDirty();
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    management.removeDirtyListener(changeListener);
  }

  @Override
  public void setClean() {
    management.setClean();
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