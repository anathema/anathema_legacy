package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.AbstractListenerClosure;
import net.sf.anathema.lib.control.GenericControl;

public class TraitChangeClosure extends AbstractListenerClosure<ICharacterChangeListener> {

  private final ITraitType traitType;

  public TraitChangeClosure(GenericControl<ICharacterChangeListener> control, ITraitType traitType) {
    super(control);
    this.traitType = traitType;
  }

  public void execute(ICharacterChangeListener input) {
    input.traitChanged(traitType);
  }
}