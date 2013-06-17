package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.Hero;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public class EssenceLimitationListener implements IIntValueChangedListener {

  private final TraitIterable traitIterable;
  private Hero hero;

  public EssenceLimitationListener(TraitIterable traitIterable, Hero hero) {
    this.traitIterable = traitIterable;
    this.hero = hero;
  }

  @Override
  public void valueChanged(int newValue) {
    if (!hero.isFullyLoaded()) {
      return;
    }
    for (Trait trait : traitIterable) {
      trait.resetCurrentValue();
    }
  }
}