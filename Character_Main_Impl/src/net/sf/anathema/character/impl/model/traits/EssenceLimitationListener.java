package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class EssenceLimitationListener implements IIntValueChangedListener {

  private final TraitProvider allTraits;
  private final ICharacterModelContext context;

  public EssenceLimitationListener(TraitProvider allTraits, ICharacterModelContext context) {
    this.allTraits = allTraits;
    this.context = context;
  }

  @Override
  public void valueChanged(int newValue) {
    if (!context.isFullyLoaded()){
      return;
    }
    for (ITrait trait : allTraits) {
      if (trait instanceof IDefaultTrait) {
        ((IDefaultTrait) trait).resetCurrentValue();
      }
    }
  }
}