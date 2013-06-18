package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;

public class LimitedTrait extends DefaultTrait {

  private final IncrementChecker incrementChecker;

  public LimitedTrait(TraitType type, ITraitTemplate template, IncrementChecker incrementChecker, TraitContext context) {
    super(new TraitRules(type, template, context.getLimitationContext()), new FriendlyValueChangeChecker(), context.getTraitValueStrategy());
    this.incrementChecker = incrementChecker;
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