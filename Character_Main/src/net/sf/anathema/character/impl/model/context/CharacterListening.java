package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.impl.model.listening.CharacterListenerMapping;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.IDefaultTrait;
import org.jmock.example.announcer.Announcer;

public class CharacterListening implements ICharacterListening {

  private final Announcer<ICharacterChangeListener> changeControl = Announcer.to(ICharacterChangeListener.class);
  private final CharacterListenerMapping listenerMapping = new CharacterListenerMapping(changeControl);

  @Override
  public void addChangeListener(ICharacterChangeListener changeListener) {
    changeControl.addListener(changeListener);
  }

  public void removeTraitListening(IDefaultTrait trait) {
    listenerMapping.removeTraitListening(trait);
  }

  public void addTraitListening(ITrait trait) {
    listenerMapping.addTraitListening(trait);
  }

  public void fireCharacterChanged() {
    changeControl.announce().changeOccurred();
  }

  public void fireCasteChanged() {
    changeControl.announce().casteChanged();
  }

  public void fireExperiencedChanged(boolean isExperienced) {
    changeControl.announce().experiencedChanged(isExperienced);
  }
}