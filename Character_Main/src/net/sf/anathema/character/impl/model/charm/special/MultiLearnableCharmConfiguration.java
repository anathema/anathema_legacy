package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.LearnRangeContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.charm.CharmTraitRequirementChecker;
import net.sf.anathema.character.impl.model.charm.PrerequisiteModifyingCharms;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.data.Range;
import org.jmock.example.announcer.Announcer;

public class MultiLearnableCharmConfiguration implements IMultiLearnableCharmConfiguration {

  private final Announcer<ISpecialCharmLearnListener> control = Announcer.to(ISpecialCharmLearnListener.class);
  private final Trait trait;
  private ICharmConfiguration config;
  private ICharm charm;
  private IMultiLearnableCharm specialCharm;
  private ICharacterModelContext context;
  private ICharmLearnableArbitrator arbitrator;

  public MultiLearnableCharmConfiguration(ICharacterModelContext context, ICharmConfiguration config, ICharm charm, IMultiLearnableCharm specialCharm,
                                          ICharmLearnableArbitrator arbitrator) {
    this.context = context;
    this.config = config;
    this.charm = charm;
    this.specialCharm = specialCharm;
    this.arbitrator = arbitrator;
    this.trait = new LimitedTrait(new TraitType(charm.getId()), SimpleTraitTemplate.createStaticLimitedTemplate(
            0, specialCharm.getAbsoluteLearnLimit()), new MultiLearnableIncrementChecker(), context.getTraitContext());
    this.trait.addCurrentValueListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        fireLearnCountChanged(newValue);
      }
    });
    context.getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void traitChanged(ITraitType type) {
        validateLearnCount();
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
    CharmTraitRequirementChecker requirementChecker =
            new CharmTraitRequirementChecker(new PrerequisiteModifyingCharms(config.getSpecialCharms()), context, config);
    return new LearnRangeContext(context.getTraitCollection(), requirementChecker, charm);
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