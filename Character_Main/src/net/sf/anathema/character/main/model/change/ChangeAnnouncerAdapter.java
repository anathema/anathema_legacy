package net.sf.anathema.character.main.model.change;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.main.concept.model.ConceptChange;

public class ChangeAnnouncerAdapter implements ChangeAnnouncer {

  private CharacterListening listening;

  public ChangeAnnouncerAdapter(CharacterListening listening) {
    this.listening = listening;
  }

  @Override
  public void announceChangeOf(ChangeFlavor flavor) {
    if (flavor == ConceptChange.FLAVOR_CASTE) {
      listening.fireCasteChanged();
    } else {
      listening.fireCharacterChanged();
    }
  }
}
