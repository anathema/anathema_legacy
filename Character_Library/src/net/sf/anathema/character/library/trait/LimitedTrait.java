package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;

public class LimitedTrait extends DefaultTrait {

  private final IIncrementChecker incrementChecker;

  // todo vom (06.01.2006) (sieroux): Den incrementChecker auf valueChangeChecker umstellen
  public LimitedTrait(
      ITraitType type,
      ITraitTemplate template,
      IIncrementChecker incrementChecker,
      ITraitContext context) {
    super(new TraitRules(type, template, context.getLimitationContext()), context, new FriendlyValueChangeChecker());
    this.incrementChecker = incrementChecker;
  }

  @Override
  public final void setCurrentValue(int value) {
    int increment = value - getCurrentValue();
    if (value >= getMinimalValue() && incrementChecker.isValidIncrement(increment)) {
      super.setCurrentValue(value);
    }
    else {
      super.resetCurrentValue();
    }
  }
}