package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.main.traits.TraitValueStrategy;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.traits.DefaultTraitMap;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.character.main.traits.context.CreationTraitValueStrategy;
import net.sf.anathema.character.main.traits.context.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.main.traits.context.ProxyTraitValueStrategy;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.util.Identifier;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class TraitModelImpl extends DefaultTraitMap implements TraitMap, TraitModel, HeroModel {

  private final ProxyTraitValueStrategy traitValueStrategy = new ProxyTraitValueStrategy(new CreationTraitValueStrategy());
  private ExperienceModel experience;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    experience = ExperienceModelFetcher.fetch(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    announcer.addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          boolean experienced = experience.isExperienced();
          updateLearnStrategies(experienced);
        }
      }
    });
  }

  private void updateLearnStrategies(boolean experienced) {
    if (experienced) {
      traitValueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    } else {
      traitValueStrategy.setStrategy(new CreationTraitValueStrategy());
    }
  }

  @Override
  public TraitValueStrategy getValueStrategy() {
     return traitValueStrategy;
  }

  @Override
  public Iterator<Trait> iterator() {
    return asList(getAll()).iterator();
  }
}