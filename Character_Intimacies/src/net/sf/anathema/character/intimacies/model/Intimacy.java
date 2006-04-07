package net.sf.anathema.character.intimacies.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;

public class Intimacy implements IIntimacy {

  private final String name;
  private final ITrait trait;
  private TraitRules traitRules;
  private final IGenericTrait maxValueTrait;

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
        return value <= maxValueTrait.getCurrentValue();
      }
    };
    this.trait = new DefaultTrait(traitRules, context.getTraitValueStrategy(), incrementChecker);
    // trait.setCurrentValue(initialValue);
  }

  public String getName() {
    return name;
  }

  public ITrait getTrait() {
    return trait;
  }

  public void resetCurrentValue() {
    int traitValue = trait.getCurrentValue();
    int maximumValue = maxValueTrait.getCurrentValue();
    if (traitValue < maximumValue) {
      return;
    }
    if (traitValue >= maximumValue) {
      trait.setCurrentValue(maximumValue);
    }
  }
}