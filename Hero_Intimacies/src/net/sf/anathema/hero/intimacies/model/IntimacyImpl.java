package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.main.library.trait.DefaultTrait;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.ValueChangeChecker;
import net.sf.anathema.character.main.library.trait.rules.TraitRules;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import org.jmock.example.announcer.Announcer;

import static net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate.createVirtueLimitedTemplate;

public class IntimacyImpl implements Intimacy {

  private final String name;
  private final Trait trait;
  private final ValuedTraitType maxValueTrait;
  private boolean complete;
  private final Announcer<IBooleanValueChangedListener> control = Announcer.to(IBooleanValueChangedListener.class);

  public IntimacyImpl(Hero hero, String name, Integer initialValue, final ValuedTraitType maxValueTrait) {
    this.name = name;
    this.maxValueTrait = maxValueTrait;
    ITraitTemplate template = createVirtueLimitedTemplate(0, initialValue, LowerableState.LowerableLoss, VirtueType.Conviction);
    TraitRules traitRules = new TraitRules(new IntimacyType(name), template, hero);
    ValueChangeChecker incrementChecker = new IntimacyValueChangeChecker(maxValueTrait);
    this.trait = new DefaultTrait(hero, traitRules, incrementChecker);
  }
  @Override
  public String getName() {
    return name;
  }

  @Override
  public Trait getTrait() {
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

  public void addChangeListener(ChangeListener listener) {
    GlobalChangeAdapter< ? > adapter = new GlobalChangeAdapter<Object>(listener);
    control.addListener(adapter);
    trait.addCurrentValueListener(adapter);
  }

  private class IntimacyValueChangeChecker implements ValueChangeChecker {
    private final ValuedTraitType maxValueTrait;

    public IntimacyValueChangeChecker(ValuedTraitType maxValueTrait) {
      this.maxValueTrait = maxValueTrait;
    }

    @Override
    public boolean isValidNewValue(int value) {
      int currentMaximum = maxValueTrait.getCurrentValue();
      boolean atMaximumValue = value == currentMaximum;
      boolean incompleteAndBelowMaximum = !complete && value < currentMaximum;
      return atMaximumValue || incompleteAndBelowMaximum;
    }
  }
}