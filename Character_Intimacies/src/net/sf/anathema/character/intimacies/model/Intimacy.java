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
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.control.booleanvalue.BooleanValueControl;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public class Intimacy implements IIntimacy {

  private final String name;
  private final IDefaultTrait trait;
  private TraitRules traitRules;
  private final IGenericTrait maxValueTrait;
  private boolean complete;
  private final BooleanValueControl control = new BooleanValueControl();

  public Intimacy(String name, Integer initialValue, final IGenericTrait maxValueTrait, ITraitContext context) {
    this.name = name;
    this.maxValueTrait = maxValueTrait;
    ITraitTemplate template = SimpleTraitTemplate.createVirtueLimitedTemplate(
        0,
        initialValue,
        LowerableState.LowerableLoss,
        VirtueType.Conviction);
    traitRules = new TraitRules(new IntimacyType(name), template, context.getLimitationContext());
    IValueChangeChecker incrementChecker = new IValueChangeChecker() {
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

  public String getName() {
    return name;
  }

  public IDefaultTrait getTrait() {
    return trait;
  }

  public void resetCurrentValue() {
    int maximumValue = maxValueTrait.getCurrentValue();
    if (complete || trait.getCurrentValue() > maximumValue) {
      trait.setCurrentValue(maximumValue);
    }
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
    resetCurrentValue();
    control.fireValueChangedEvent(this.complete);
  }

  public void addCompletionListener(IBooleanValueChangedListener listener) {
    control.addValueChangeListener(listener);
  }

  public boolean isComplete() {
    return complete;
  }
}