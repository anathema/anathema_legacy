package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmLearnableArbitrator;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.data.Range;

public class MultiLearnableCharmConfiguration implements IMultiLearnableCharmConfiguration {

  private final GenericControl<ISpecialCharmLearnListener> control = new GenericControl<ISpecialCharmLearnListener>();
  private final ICharm charm;
  private final IModifiableTrait trait;

  public MultiLearnableCharmConfiguration(
      final ICharacterModelContext context,
      ICharm charm,
      final IMultiLearnableCharm specialCharm,
      final ICharmLearnableArbitrator arbitrator) {
    this.charm = charm;
    this.trait = new LimitedTrait(OtherTraitType.SpecialCharm, SimpleTraitTemplate.createStaticLimitedTemplate(
        0,
        specialCharm.getAbsoluteLearnLimit()), new IIncrementChecker() {
      public boolean isValidIncrement(int increment) {
        Range allowedRange = new Range(0, specialCharm.getMaximumLearnCount(context.getTraitCollection()));
        int incrementedValue = trait.getCurrentValue() + increment;
        if (incrementedValue == 0) {
          return true;
        }
        boolean learnable = arbitrator.isLearnable(getCharm());
        return learnable && allowedRange.contains(incrementedValue);
      }
    }, context.getTraitContext());
    trait.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        fireLearnCountChanged(newValue);
      }
    });
    arbitrator.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(ICharm forgottenCharm) {
        if (!forgottenCharm.getId().equals(getCharm().getId())) {
          return;
        }
        if (trait.getCurrentValue() != 0) {
          trait.setCurrentValue(0);
        }
      }
    });
  }

  public int getCreationLearnCount() {
    return trait.getCreationValue();
  }

  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  private synchronized void fireLearnCountChanged(final int learnCount) {
    control.forAllDo(new IClosure<ISpecialCharmLearnListener>() {
      public void execute(ISpecialCharmLearnListener input) {
        input.learnCountChanged(learnCount);
      }
    });
  }

  public ICharm getCharm() {
    return charm;
  }

  public IModifiableTrait getCategory() {
    return trait;
  }

  public int getCurrentLearnCount() {
    return trait.getCurrentValue();
  }

  public void setCurrentLearnCount(int newValue) {
    trait.setCurrentValue(newValue);
  }
}