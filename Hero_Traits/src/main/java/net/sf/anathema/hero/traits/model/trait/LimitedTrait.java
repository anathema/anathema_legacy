package net.sf.anathema.hero.traits.model.trait;

import net.sf.anathema.hero.traits.model.DefaultTrait;
import net.sf.anathema.hero.traits.model.FriendlyValueChangeChecker;
import net.sf.anathema.hero.traits.model.IncrementChecker;
import net.sf.anathema.hero.traits.model.TraitRules;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.template.TraitTemplate;

public class LimitedTrait extends DefaultTrait {

  private final IncrementChecker incrementChecker;

  public LimitedTrait(Hero hero, TraitType type, TraitTemplate template, IncrementChecker incrementChecker) {
    super(hero, createTraitRules(hero, type, template), new FriendlyValueChangeChecker());
    this.incrementChecker = incrementChecker;
  }

  private static TraitRules createTraitRules(Hero hero, TraitType type, TraitTemplate template) {
    return new TraitRulesImpl(type, template, hero);
  }

  @Override
  public final void setCurrentValue(int value) {
    int increment = value - getCurrentValue();
    if (value >= getMinimalValue() && incrementChecker.isValidIncrement(increment)) {
      super.setCurrentValue(value);
    } else {
      super.resetCurrentValue();
    }
  }
}