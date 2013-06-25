package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public abstract class AbstractVirtueFlawModel implements VirtueFlawModel {

  private Hero hero;

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.hero = hero;
  }

  @Override
  public boolean isVirtueFlawChangable() {
    return !ExperienceModelFetcher.fetch(hero).isExperienced();
  }

  public void addChangeListener(ChangeListener listener) {
    GlobalChangeAdapter<String> adapter = new GlobalChangeAdapter<>(listener);
    getVirtueFlaw().addRootChangeListener(listener);
    getVirtueFlaw().getName().addTextChangedListener(adapter);
    getVirtueFlaw().getLimitTrait().addCurrentValueListener(adapter);
  }

  @Override
  public void addVirtueFlawChangableListener(final IBooleanValueChangedListener listener) {
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          listener.valueChanged(isVirtueFlawChangable());
        }
      }
    });
  }

}