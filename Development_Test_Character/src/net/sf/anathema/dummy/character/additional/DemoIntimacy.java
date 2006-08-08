package net.sf.anathema.dummy.character.additional;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.model.IntimacyType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public class DemoIntimacy implements IIntimacy{

  private final String name;
  private final int value;
  private final IGenericCharacter character;

  public DemoIntimacy(String name, int value, IGenericCharacter character) {
    this.name = name;
    this.value = value;
    this.character = character;
  }
  
  public void addCompletionListener(IBooleanValueChangedListener listener) {
    //Nothing to do
  }

  public String getName() {
    return name;
  }

  public IModifiableTrait getTrait() {
    ITraitRules traitRules = createTraitRules();
    DefaultTrait trait = new DefaultTrait(traitRules, new CreationTraitValueStrategy(), new FriendlyValueChangeChecker());
    trait.setCurrentValue(value);
    return trait;
  }

  private ITraitRules createTraitRules() {
    ITraitTemplate traitTemplate = SimpleTraitTemplate.createStaticLimitedTemplate(0, 3);
    return new TraitRules(new IntimacyType(getName()), traitTemplate, character);
  }

  public boolean isComplete() {
    return value >= 3;
  }

  public void resetCurrentValue() {
    //Nothing to do
  }

  public void setComplete(boolean complete) {
    //Nothing to do
  }
}