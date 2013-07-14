package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.OxBodyCategory;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.health.IHealthLevelProvider;
import net.sf.anathema.hero.health.OxBodyTechniqueArbitrator;
import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.control.IntValueChangedListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OxBodyTechniqueConfiguration implements OxBodyTechniqueSpecials {

  private final Announcer<ISpecialCharmLearnListener> control = Announcer.to(ISpecialCharmLearnListener.class);
  private final IncrementChecker incrementChecker;
  private final OxBodyCategory[] categories;
  private final Charm oxBodyTechnique;
  private final IHealthLevelProvider healthLevelProvider;

  public OxBodyTechniqueConfiguration(final Hero hero, Charm oxBodyTechnique, final TraitType[] relevantTraits,
                                      final OxBodyTechniqueArbitrator arbitrator, IOxBodyTechniqueCharm properties) {
    this.oxBodyTechnique = oxBodyTechnique;
    incrementChecker = new IncrementChecker() {
      @Override
      public boolean isValidIncrement(int increment) {
        int minTrait = Integer.MAX_VALUE;
        for (TraitType type : relevantTraits) {
          TraitModel traits = TraitModelFetcher.fetch(hero);
          Trait trait = traits.getTrait(type);
          minTrait = Math.min(minTrait, trait.getCurrentValue());
        }
        return increment < 0 || (arbitrator.isIncrementAllowed(increment) && getCurrentLearnCount() + increment <= minTrait);
      }
    };
    categories = createOxBodyCategories(hero, properties);
    for (OxBodyCategory category : categories) {
      category.addCurrentValueListener(new IntValueChangedListener() {
        @Override
        public void valueChanged(int newValue) {
          fireLearnCountChanged(getCurrentLearnCount());
        }
      });
    }
    this.healthLevelProvider = new OxBodyTechniqueHealthLevelProvider(categories);
  }

  private OxBodyCategory[] createOxBodyCategories(Hero hero, IOxBodyTechniqueCharm properties) {
    Set<String> ids = properties.getHealthLevels().keySet();
    List<OxBodyCategory> categoryList = new ArrayList<>();
    for (String id : ids) {
      HealthLevelType[] levels = properties.getHealthLevels().get(id);
      OxBodyCategory category = new OxBodyCategory(hero, levels, id, incrementChecker);
      categoryList.add(category);
    }
    return categoryList.toArray(new OxBodyCategory[categoryList.size()]);
  }

  @Override
  public void forget() {
    for (OxBodyCategory category : getCategories()) {
      category.setCurrentValue(0);
    }
  }

  @Override
  public void learn(boolean experienced) {
    OxBodyCategory trait = getCategories()[0];
    if (experienced && getCurrentLearnCount() == 0) {
      trait.setExperiencedValue(1);
    } else if (!experienced && getCreationLearnCount() == 0) {
      trait.setCreationValue(1);
    }
  }

  @Override
  public OxBodyCategory[] getCategories() {
    return categories;
  }

  @Override
  public int getCreationLearnCount() {
    int sum = 0;
    for (OxBodyCategory category : getCategories()) {
      sum += category.getCreationValue();
    }
    return sum;
  }

  @Override
  public int getCurrentLearnCount() {
    int sum = 0;
    for (OxBodyCategory category : getCategories()) {
      sum += category.getCurrentValue();
    }
    return sum;
  }

  @Override
  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  private void fireLearnCountChanged(int learnCount) {
    control.announce().learnCountChanged(learnCount);
  }

  @Override
  public Charm getCharm() {
    return oxBodyTechnique;
  }

  @Override
  public OxBodyCategory getCategoryById(String id) {
    for (OxBodyCategory category : categories) {
      if (category.getId().equals(id)) {
        return category;
      }
    }
    throw new IllegalArgumentException("No ox body category found with id " + id);
  }

  @Override
  public IHealthLevelProvider getHealthLevelProvider() {
    return healthLevelProvider;
  }
}