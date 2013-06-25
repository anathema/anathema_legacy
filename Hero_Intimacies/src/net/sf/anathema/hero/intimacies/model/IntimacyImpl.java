package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import org.jmock.example.announcer.Announcer;

import static net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate.createVirtueLimitedTemplate;

public class IntimacyImpl implements Intimacy {

  private final String name;
  private final Trait trait;
  private final GenericTrait maxValueTrait;
  private boolean complete;
  private final Announcer<IBooleanValueChangedListener> control = Announcer.to(IBooleanValueChangedListener.class);

  public IntimacyImpl(Hero hero, String name, Integer initialValue, final GenericTrait maxValueTrait) {
    this.name = name;
    this.maxValueTrait = maxValueTrait;
    ITraitTemplate template = createVirtueLimitedTemplate(0, initialValue, LowerableState.LowerableLoss, VirtueType.Conviction);
    TraitRules traitRules = new TraitRules(new IntimacyType(name), template, hero);
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
}