package net.sf.anathema.character.impl.model.listening;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.TraitChangeClosure;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class TraitListener implements IIntValueChangedListener {

  private TraitChangeClosure closure;

  public TraitListener(GenericControl<ICharacterChangeListener> control, ITraitType traitType) {
    this.closure = new TraitChangeClosure(control, traitType);
  }

  public void valueChanged(int newValue) {
    closure.fireEvent();
  }
}