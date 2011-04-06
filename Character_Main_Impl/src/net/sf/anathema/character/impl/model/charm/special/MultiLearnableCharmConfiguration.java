package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.data.Range;

public class MultiLearnableCharmConfiguration implements IMultiLearnableCharmConfiguration {

  private final GenericControl<ISpecialCharmLearnListener> control = new GenericControl<ISpecialCharmLearnListener>();
  private final IDefaultTrait trait;
  private ICharmConfiguration config;
  private ICharm charm;
  private IMultiLearnableCharm specialCharm;
  private ICharacterModelContext context;
  private ICharmLearnableArbitrator arbitrator;
  
  public MultiLearnableCharmConfiguration(
	      final ICharacterModelContext context,
	      final ICharmConfiguration config,
	      final ICharm charm,
	      final IMultiLearnableCharm specialCharm,
	      final ICharmLearnableArbitrator arbitrator){
	this.context = context;
	this.config = config;
	this.charm = charm;
	this.specialCharm = specialCharm;
	this.arbitrator = arbitrator;
    this.trait = new LimitedTrait(new TraitType("SpecialCharm"), SimpleTraitTemplate.createStaticLimitedTemplate( //$NON-NLS-1$
	        0,
	        specialCharm.getAbsoluteLearnLimit()), new MultiLearnableIncrementChecker(), 
	        context.getTraitContext());
    this.trait.addCurrentValueListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        fireLearnCountChanged(newValue);
      }
    });
  }
  
  @Override
  public void forget() {
    trait.setCurrentValue(0);
  }
  
  @Override
  public void learn(boolean experienced) {
    if (experienced && getCurrentLearnCount() == 0) {
      trait.setExperiencedValue(1);
    }
    else if (getCreationLearnCount() == 0) {
      trait.setCreationValue(1);
    }
  }

  public int getCreationLearnCount() {
    return trait.getCreationValue();
  }

  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    control.addListener(listener);
  }

  private void fireLearnCountChanged(final int learnCount) {
    control.forAllDo(new IClosure<ISpecialCharmLearnListener>() {
      public void execute(ISpecialCharmLearnListener input) {
        input.learnCountChanged(learnCount);
      }
    });
  }

  public ICharm getCharm() {
    return charm;
  }

  public IDefaultTrait getCategory() {
    return trait;
  }

  public int getCurrentLearnCount() {
    return trait.getCurrentValue();
  }

  public void setCurrentLearnCount(int newValue) {
    trait.setCurrentValue(newValue);
  }
  
  private class MultiLearnableIncrementChecker implements IIncrementChecker
  {
      public boolean isValidIncrement(int increment) {
    	int mergedDots = getMergedDots();
        Range allowedRange = new Range(0, specialCharm.getMaximumLearnCount(context.getTraitCollection()) - mergedDots);
        int incrementedValue = MultiLearnableCharmConfiguration.this.trait.getCurrentValue() + increment;
        if (incrementedValue == 0) {
          return true;
        }
        boolean learnable = arbitrator.isLearnable(charm);
        return learnable && allowedRange.contains(incrementedValue);
      }
      
      private int getMergedDots()
      {
    	  int dots = 0;
    	  for (ICharm mergedCharm : charm.getMergedCharms())
    		  dots += mergedCharm == charm ? 0 : config.getSpecialCharmConfiguration(mergedCharm).getCurrentLearnCount();
    	  return dots;
      }
    }
}