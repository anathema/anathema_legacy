package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class Intimacy implements IIntimacy {

  private final String name;
  private final IDefaultTrait trait;
  private final IGenericTrait maxValueTrait;
  private boolean complete;
  private final Announcer<IBooleanValueChangedListener> control = Announcer.to(IBooleanValueChangedListener.class);

  public Intimacy(String name, Integer initialValue, final IGenericTrait maxValueTrait, ITraitContext context) {
    this.name = name;
    this.maxValueTrait = maxValueTrait;
    ITraitTemplate template = SimpleTraitTemplate.createVirtueLimitedTemplate(
        0,
        initialValue,
        LowerableState.LowerableLoss,
        VirtueType.Conviction);
    TraitRules traitRules = new TraitRules(new IntimacyType(name), template, context.getLimitationContext());
    IValueChangeChecker incrementChecker = new IValueChangeChecker() {
      @Override
      public boolean isValidNewValue(int value) {
        int currentMaximum = maxValueTrait.getCurrentValue();
        if (value == currentMaximum) {
          return true;
        }
        return !complete && value < currentMaximum;
      }
    };
    this.trait = new DefaultTrait(traitRules, context, incrementChecker);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public IDefaultTrait getTrait() {
    return trait;
  }

  @Override
  public void resetCurrentValue() {
    int maximumValue = maxValueTrait.getCurrentValue();
    if (complete || trait.getCurrentValue() > maximumValue) {
      trait.setCurrentValue(maximumValue);
    }
  }

  @Override
  public void setComplete(boolean complete) {
    this.complete = complete;
    resetCurrentValue();
    control.announce().valueChanged(this.complete);
  }

  @Override
  public void addCompletionListener(IBooleanValueChangedListener listener) {
    control.addListener(listener);
  }

  @Override
  public boolean isComplete() {
    return complete;
  }

  public void addChangeListener(IChangeListener listener) {
    GlobalChangeAdapter< ? > adapter = new GlobalChangeAdapter<Object>(listener);
    control.addListener(adapter);
    trait.addCurrentValueListener(adapter);
  }
}