package net.sf.anathema.character.impl.model.listening;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public class TraitListener implements IIntValueChangedListener {

  private final Announcer<ICharacterChangeListener> control;
  private final ITraitType traitType;

  public TraitListener(Announcer<ICharacterChangeListener> control, ITraitType traitType) {
    this.control = control;
    this.traitType = traitType;
  }

  @Override
  public void valueChanged(int newValue) {
    control.announce().traitChanged(traitType);
  }
}