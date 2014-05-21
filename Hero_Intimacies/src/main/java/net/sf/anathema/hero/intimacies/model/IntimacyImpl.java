package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.hero.traits.model.DefaultTrait;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.ValueChangeChecker;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.trait.ModificationType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.types.VirtueType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.trait.TraitRulesImpl;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class IntimacyImpl implements Intimacy {

  private final String name;
  private final Trait trait;
  private final ValuedTraitType maxValueTrait;
  private boolean complete;
  private final Announcer<IBooleanValueChangedListener> control = Announcer.to(IBooleanValueChangedListener.class);

  public IntimacyImpl(Hero hero, String name, Integer initialValue, final ValuedTraitType maxValueTrait) {
    this.name = name;
    this.maxValueTrait = maxValueTrait;
    TraitTemplate template = createIntimacyTemplate(0, initialValue, ModificationType.Free, VirtueType.Conviction);
    TraitRules traitRules = new TraitRulesImpl(new IntimacyType(name), template, hero);
    ValueChangeChecker incrementChecker = new IntimacyValueChangeChecker(maxValueTrait);
    this.trait = new DefaultTrait(hero, traitRules, incrementChecker);
  }

  private static TraitTemplate createIntimacyTemplate(int minimumValue, int startValue, ModificationType state, VirtueType type) {
    TraitTemplate template = new TraitTemplate();
    template.minimumValue = minimumValue;
    template.startValue = startValue;
    template.modificationType = state;
    LimitationTemplate limitation = new LimitationTemplate();
    limitation.type = LimitationType.Static;
    limitation.value = 4;
    template.limitation = limitation;
    return template;
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