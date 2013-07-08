package net.sf.anathema.hero.magic.model.charms.special;

import net.sf.anathema.character.main.library.trait.DefaultTraitType;
import net.sf.anathema.character.main.library.trait.LimitedTrait;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialist;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialistImpl;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.PrerequisiteModifyingCharms;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.main.magic.model.charm.special.LearnRangeContext;
import net.sf.anathema.character.main.magic.model.charmtree.CharmTraitRequirementChecker;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitChangeFlavor;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.data.Range;
import org.jmock.example.announcer.Announcer;

import static net.sf.anathema.character.main.traits.SimpleTraitTemplate.createStaticLimitedTemplate;

public class MultiLearnableCharmConfiguration implements IMultiLearnableCharmConfiguration {

  private final Announcer<ISpecialCharmLearnListener> control = Announcer.to(ISpecialCharmLearnListener.class);
  private final Trait trait;
  private CharmsModel config;
  private ICharm charm;
  private IMultiLearnableCharm specialCharm;
  private CharmSpecialist specialist;
  private ICharmLearnableArbitrator arbitrator;
  private Hero hero;

  public MultiLearnableCharmConfiguration(Hero hero, CharmsModel config, ICharm charm, IMultiLearnableCharm specialCharm, ICharmLearnableArbitrator arbitrator) {
    this.hero = hero;
    this.specialist = new CharmSpecialistImpl(hero);
    this.config = config;
    this.charm = charm;
    this.specialCharm = specialCharm;
    this.arbitrator = arbitrator;
    this.trait = new LimitedTrait(hero, new DefaultTraitType(charm.getId()), createStaticLimitedTemplate(0, specialCharm.getAbsoluteLearnLimit()),
            new MultiLearnableIncrementChecker());
    this.trait.addCurrentValueListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        fireLearnCountChanged(newValue);
      }
    });
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor instanceof TraitChangeFlavor) {
          validateLearnCount();
        }
      }
    });
  }

  @Override
  public void forget() {
    trait.setCurrentValue(0);
  }

  @Override
  public void learn(boolean experienced) {
    int minimumLearnCount = specialCharm.getMinimumLearnCount(createLearnRangeContext());
    if (experienced) {
      if (getCurrentLearnCount() == 0) {
        trait.setExperiencedValue(minimumLearnCount);
      }
    } else if (getCreationLearnCount() == 0) {
      trait.setCreationValue(minimumLearnCount);
    }
  }

  @Override
  public int getCreationLearnCount() {
    return trait.getCreationValue();
  }

  @Override
  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  private void fireLearnCountChanged(int learnCount) {
    control.announce().learnCountChanged(learnCount);
  }

  @Override
  public ICharm getCharm() {
    return charm;
  }

  @Override
  public Trait getCategory() {
    return trait;
  }

  @Override
  public int getCurrentLearnCount() {
    return trait.getCurrentValue();
  }

  @Override
  public void setCurrentLearnCount(int newValue) {
    trait.setCurrentValue(newValue);
  }

  private void validateLearnCount() {
    if (trait.getCurrentValue() == 0) {
      return;
    }
    Range range = getRange();
    if (trait.getCurrentValue() < range.getLowerBound()) {
      setCurrentLearnCount(range.getLowerBound());
    }
    if (trait.getCurrentValue() > range.getUpperBound()) {
      setCurrentLearnCount(range.getUpperBound());
    }
  }

  private Range getRange() {
    int mergedDots = getMergedDots();
    int minValue = specialCharm.getMinimumLearnCount(createLearnRangeContext()) - mergedDots;
    int maxValue = specialCharm.getMaximumLearnCount(createLearnRangeContext()) - mergedDots;
    return new Range(minValue, maxValue);
  }

  private LearnRangeContext createLearnRangeContext() {
    PrerequisiteModifyingCharms modifyingCharms = new PrerequisiteModifyingCharms(config.getSpecialCharms());
    CharmTraitRequirementChecker requirementChecker = new CharmTraitRequirementChecker(modifyingCharms, specialist.getTraits(), config);
    return new LearnRangeContext(TraitModelFetcher.fetch(hero), requirementChecker, charm);
  }

  private int getMergedDots() {
    int dots = 0;
    for (ICharm mergedCharm : charm.getMergedCharms()) {
      dots += mergedCharm == charm ? 0 : config.getSpecialCharmConfiguration(mergedCharm).getCurrentLearnCount();
    }
    return dots;
  }

  private class MultiLearnableIncrementChecker implements IncrementChecker {
    @Override
    public boolean isValidIncrement(int increment) {
      int incrementedValue = MultiLearnableCharmConfiguration.this.trait.getCurrentValue() + increment;
      if (incrementedValue == 0) {
        return true;
      }
      boolean learnable = arbitrator.isLearnable(charm);
      return learnable && getRange().contains(incrementedValue);
    }
  }
}