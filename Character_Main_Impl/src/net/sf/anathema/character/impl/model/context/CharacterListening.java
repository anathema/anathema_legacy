package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.impl.model.listening.CharacterListenerMapping;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class CharacterListening implements ICharacterListening {

  private final GenericControl<ICharacterChangeListener> changeControl = new GenericControl<ICharacterChangeListener>();
  private final CharacterListenerMapping listenerMapping = new CharacterListenerMapping(changeControl);

  public void addChangeListener(ICharacterChangeListener changeListener) {
    changeControl.addListener(changeListener);
  }

  public void removeTraitListening(IModifiableTrait trait) {
    listenerMapping.removeTraitListening(trait);
  }
    
  public void addTraitListening(IModifiableTrait trait) {
    listenerMapping.addTraitListening(trait);
  }

  public void fireCharacterChanged() {
    changeControl.forAllDo(new IClosure<ICharacterChangeListener>() {
      public void execute(ICharacterChangeListener listener) {
        listener.characterChanged();
      }
    });
  }

  public void fireCasteChanged() {
    changeControl.forAllDo(new IClosure<ICharacterChangeListener>() {
      public void execute(ICharacterChangeListener listener) {
        listener.casteChanged();
      }
    });
  }

  public void fireExperiencedChanged(final boolean isExperienced) {
    changeControl.forAllDo(new IClosure<ICharacterChangeListener>() {
      public void execute(ICharacterChangeListener listener) {
        listener.experiencedChanged(isExperienced);
      }
    });
  }
}