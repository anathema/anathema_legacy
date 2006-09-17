/**
 * 
 */
package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;

public final class CharacterChangeCharmListener extends CharmLearnAdapter {
  
  private final CharacterListening listening;

  public CharacterChangeCharmListener(CharacterListening listening) {
    this.listening = listening;
  }
  
  @Override
  public void charmForgotten(ICharm charm) {
    listening.fireCharacterChanged();
  }

  @Override
  public void charmLearned(ICharm charm) {
    listening.fireCharacterChanged();
  }

  @Override
  public void recalculateRequested() {
    listening.fireCharacterChanged();
  }
}