package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public class EssenceLimitationListener implements IIntValueChangedListener {

  private final TraitIterable traitIterable;
  private final ICharacterModelContext context;

  public EssenceLimitationListener(TraitIterable traitIterable, ICharacterModelContext context) {
    this.traitIterable = traitIterable;
    this.context = context;
  }

  @Override
  public void valueChanged(int newValue) {
    if (!context.isFullyLoaded()) {
      return;
    }
    for (Trait trait : traitIterable) {
      trait.resetCurrentValue();
    }
  }
}